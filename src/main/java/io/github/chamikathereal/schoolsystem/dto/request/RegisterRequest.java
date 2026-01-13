package io.github.chamikathereal.schoolsystem.dto.request;

import io.github.chamikathereal.schoolsystem.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role; // ADMIN, TEACHER, etc.
}