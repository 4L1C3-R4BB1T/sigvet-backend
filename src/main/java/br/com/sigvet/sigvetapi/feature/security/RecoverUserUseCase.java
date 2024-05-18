package br.com.sigvet.sigvetapi.feature.security;

import java.util.ArrayList;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.repositories.UserRepository;
import br.com.sigvet.sigvetapi.common.utils.StringNormalizer;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class RecoverUserUseCase {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    
    @Transactional
    public boolean execute(final RecoverUserRequestDTO request) {

        final var errors = new ArrayList<String>();

        if (!userRepository.existsByEmailAndDocument(
                StringNormalizer.normalizeString(request.email()), 
                StringNormalizer.removeNonNumericCharacteres(request.document()))) {
                errors.add("We did not find any users with these identifiers");
        }

        if (!StringNormalizer.normalizeString(request.password()).equals(request.confirmationPassword())) {
            errors.add("Password does not match confirmation password");
        }

        if (!errors.isEmpty()) {
            throw new ApplicationException("Recover Error", errors);
        }

        final var user = userRepository.findByEmail(StringNormalizer.normalizeString(request.email())).get();
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
        return true;
    }
}
