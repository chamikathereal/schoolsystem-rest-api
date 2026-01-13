package io.github.chamikathereal.schoolsystem.service.impl;

import io.github.chamikathereal.schoolsystem.dto.request.LoginRequest;
import io.github.chamikathereal.schoolsystem.dto.request.RegisterRequest;
import io.github.chamikathereal.schoolsystem.dto.response.AuthResponse;
import io.github.chamikathereal.schoolsystem.entity.User;
import io.github.chamikathereal.schoolsystem.repository.UserRepository;
import io.github.chamikathereal.schoolsystem.service.interfaces.AuthService; // Import the Interface
import io.github.chamikathereal.schoolsystem.utils.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        // 1. Check if user exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }

        // 2. Create User Entity
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        // 3. Save to DB
        userRepository.save(user);

        // 4. Generate Token
        var jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        // 1. Authenticate (Checks username/password)
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // 2. If valid, fetch user
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 3. Generate Token
        var jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}