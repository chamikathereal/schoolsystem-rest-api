package io.github.chamikathereal.schoolsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    // We use one DTO for both Creation and Updates for simplicity
    private String firstName;
    private String lastName;
    private String email;
    private String mobile;
    private String address;
    private LocalDate dob;
}