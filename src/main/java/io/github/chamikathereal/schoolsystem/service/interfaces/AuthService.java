package io.github.chamikathereal.schoolsystem.service.interfaces;

import io.github.chamikathereal.schoolsystem.dto.request.LoginRequest;
import io.github.chamikathereal.schoolsystem.dto.request.RegisterRequest;
import io.github.chamikathereal.schoolsystem.dto.response.AuthResponse;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}