package br.com.sigvet.sigvetapi.feature.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.sigvet.sigvetapi.common.repositories.UserRepository;
import br.com.sigvet.sigvetapi.feature.veterinarian.VeterinarianRepository;
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
                    .id(userEntity.getId())
                    .email(userEntity.getEmail())
                    .password(userEntity.getPassword())
                    .roles(userEntity.getRoles())
                    .build();
            })
            .orElseThrow(() -> new UsernameNotFoundException("Unable to find user"));
    } 
}
