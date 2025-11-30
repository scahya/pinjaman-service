package escahya.core.pinjamanservice.services;

import escahya.core.pinjamanservice.config.jwt.JwtUtil;
import escahya.core.pinjamanservice.dtos.RegisterDto;
import escahya.core.pinjamanservice.entities.Users;
import escahya.core.pinjamanservice.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsersRepository usersRepository;
    private final JwtUtil jwtUtil;


    public void registerUser(@RequestBody RegisterDto registerDto) {

        Users user = new Users();
        user.setUsername(registerDto.getUsername());
        user.setPassword(registerDto.getPassword());
        user.setNamaLengkap(registerDto.getNamaLengkap());
        user.setRole(registerDto.getRole());

        usersRepository.save(user);

    }

    public String login(String username, String password) {

        Users user = usersRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Wrong password");
        }

        // Generate token isi username + role
        return jwtUtil.generateToken(user.getUsername(), String.valueOf(user.getRole()));
    }
}
