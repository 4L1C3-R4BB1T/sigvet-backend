package br.com.sigvet.sigvetapi.feature.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.sigvet.sigvetapi.feature.user.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserSecurityService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(final String emailOrUsername) throws UsernameNotFoundException {
        return repository.findByEmailOrUsername(emailOrUsername, emailOrUsername)
            .map(userEntity -> {
                return User.builder()
                    .id(userEntity.getId())
                    .username(userEntity.getUsername())
                    .email(userEntity.getEmail())
                    .password(userEntity.getPassword())
                    .roles(userEntity.getRoles())
                    .build();
            })
            .orElseThrow(() -> new UsernameNotFoundException("Unable to find user"));
    } 
}
