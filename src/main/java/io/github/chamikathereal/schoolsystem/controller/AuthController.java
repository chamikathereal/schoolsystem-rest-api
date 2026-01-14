package io.github.chamikathereal.schoolsystem.controller;

import io.github.chamikathereal.schoolsystem.dto.request.LoginRequest;
import io.github.chamikathereal.schoolsystem.dto.request.RegisterRequest;
import io.github.chamikathereal.schoolsystem.dto.response.AuthResponse;
import io.github.chamikathereal.schoolsystem.service.interfaces.AuthService; // <--- IMPORTANT: Use the Interface package
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/me")
    public ResponseEntity<String> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok("User: " + auth.getName() + ", Roles: " + auth.getAuthorities());
    }
}