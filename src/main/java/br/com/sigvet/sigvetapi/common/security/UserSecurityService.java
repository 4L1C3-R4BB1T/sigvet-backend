package br.com.sigvet.sigvetapi.common.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.sigvet.sigvetapi.common.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(final String email) throws UsernameNotFoundException {
        return repository.findByEmail(email)
            .map(userEntity -> {
                return User.builder()
                    .email(userEntity.getEmail())
                    .password(userEntity.getPassword())
                    .build();
            })
            .orElseThrow(() -> new UsernameNotFoundException("Unable to find user"));
    }
    
}
