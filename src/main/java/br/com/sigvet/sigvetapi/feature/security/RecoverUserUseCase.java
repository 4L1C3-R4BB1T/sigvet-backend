package br.com.sigvet.sigvetapi.feature.security;

import java.util.ArrayList;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.Notification;
import br.com.sigvet.sigvetapi.common.utils.StringNormalizer;
import br.com.sigvet.sigvetapi.feature.user.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class RecoverUserUseCase {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    
    @Transactional
    public boolean execute(final RecoverUserRequestDTO request) {

        final var notification = new Notification();

        if (!userRepository.existsByEmailAndDocument(
                StringNormalizer.normalizeString(request.email()), 
                StringNormalizer.removeNonNumericCharacteres(request.document()))) {
                notification.addError("Não encontramos nenhum usuário com essas informações");
        }

        if (!StringNormalizer.normalizeString(request.password()).equals(request.confirmationPassword())) {
            notification.addError("A senha não corresponde à senha de confirmação");
        }

        if (notification.hasAnyError()) {
            throw new ApplicationException(notification.getErrors());
        }

        final var user = userRepository.findByEmail(StringNormalizer.normalizeString(request.email())).get();
        user.setPassword(passwordEncoder.encode(request.password()));
        userRepository.save(user);
        return true;
    }
}
