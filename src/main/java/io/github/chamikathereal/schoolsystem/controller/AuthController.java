package io.github.chamikathereal.schoolsystem.controller;

import io.github.chamikathereal.schoolsystem.dto.request.LoginRequest;
import io.github.chamikathereal.schoolsystem.dto.request.RegisterRequest;
import io.github.chamikathereal.schoolsystem.dto.response.AuthResponse;
import io.github.chamikathereal.schoolsystem.service.interfaces.AuthService; // <--- IMPORTANT: Use the Interface package
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService; // This now refers to the Interface

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}