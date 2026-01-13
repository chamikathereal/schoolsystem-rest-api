package io.github.chamikathereal.schoolsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true) // Database level safety
    private String email;

    private String mobile;
    private String address;

    @Column(nullable = false)
    private LocalDate dob; // Date of Birth (YYYY-MM-DD)

    // Note: We do NOT have an 'age' field here.
    // Storing age is bad practice because it becomes outdated tomorrow.
}