package escahya.core.pinjamanservice.controllers;

import escahya.core.pinjamanservice.dtos.JwtResponse;
import escahya.core.pinjamanservice.dtos.LoginDto;
import escahya.core.pinjamanservice.dtos.RegisterDto;
import escahya.core.pinjamanservice.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody RegisterDto registerDto) {

        authService.registerUser(registerDto);
        return new ResponseEntity<>("titit", HttpStatus.OK);
    }


    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto request) {

        String token = authService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
