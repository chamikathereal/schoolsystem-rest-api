package io.github.chamikathereal.schoolsystem.service.impl;

import io.github.chamikathereal.schoolsystem.dto.request.LoginRequest;
import io.github.chamikathereal.schoolsystem.dto.request.RegisterRequest;
import io.github.chamikathereal.schoolsystem.dto.response.AuthResponse;
import io.github.chamikathereal.schoolsystem.entity.User;
import io.github.chamikathereal.schoolsystem.exception.DuplicateResourceException; // Import
import io.github.chamikathereal.schoolsystem.exception.ResourceNotFoundException; // Import
import io.github.chamikathereal.schoolsystem.repository.UserRepository;
import io.github.chamikathereal.schoolsystem.service.interfaces.AuthService;
import io.github.chamikathereal.schoolsystem.utils.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j; // Import
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j // Add Logger
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse register(RegisterRequest request) {
        log.info("Registering new user: {}", request.getEmail());

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            log.warn("Registration failed: Email {} already exists", request.getEmail());
            throw new DuplicateResourceException("Email already exists");
        }

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        log.info("User {} registered successfully. Role: {}", user.getEmail(), user.getRole());
        return AuthResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        log.info("User {} attempting to login", request.getEmail());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            log.warn("Login failed for {}: Invalid credentials", request.getEmail());
            throw new ResourceNotFoundException("Invalid email or password");
        }

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        var jwtToken = jwtService.generateToken(user);

        log.info("User {} logged in successfully", request.getEmail());
        return AuthResponse.builder().token(jwtToken).build();
    }
}