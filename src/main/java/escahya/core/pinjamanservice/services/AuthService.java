package escahya.core.pinjamanservice.services;

import escahya.core.pinjamanservice.config.jwt.JwtUtil;
import escahya.core.pinjamanservice.dtos.JwtResponse;
import escahya.core.pinjamanservice.dtos.RegisterReqDto;
import escahya.core.pinjamanservice.entities.Users;
import escahya.core.pinjamanservice.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsersRepository usersRepository;
    private final JwtUtil jwtUtil;


    public Users registerUser(RegisterReqDto registerReqDto) {

        Users user = new Users();
        user.setUsername(registerReqDto.getUsername());
        user.setPassword(registerReqDto.getPassword());
        user.setNamaLengkap(registerReqDto.getNamaLengkap());
        user.setRole(registerReqDto.getRole());

       return usersRepository.save(user);

    }

    public JwtResponse login(String username, String password) {

        Users user = usersRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Wrong password");
        }

        // Generate token isi username + role
        JwtResponse jwtResponse = new JwtResponse();
        jwtResponse.setRole(String.valueOf(user.getRole()));
        jwtResponse.setToken(jwtUtil.generateToken(user.getUsername(), String.valueOf(user.getRole())));

        return jwtResponse;
    }
}
