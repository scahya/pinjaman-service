package escahya.core.pinjamanservice.services;

import escahya.core.pinjamanservice.entities.Users;
import escahya.core.pinjamanservice.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        Users user = repository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Not found"));

        return org.springframework.security.core.userdetails.User
            .builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .roles(String.valueOf(user.getRole()))
            .build();
    }
}

