package io.github.chamikathereal.schoolsystem.service.impl;

import io.github.chamikathereal.schoolsystem.dto.request.StudentDto;
import io.github.chamikathereal.schoolsystem.dto.response.StudentResponseDto;
import io.github.chamikathereal.schoolsystem.entity.Student;
import io.github.chamikathereal.schoolsystem.exception.DuplicateResourceException; // Import
import io.github.chamikathereal.schoolsystem.exception.ResourceNotFoundException; // Import
import io.github.chamikathereal.schoolsystem.repository.StudentRepository;
import io.github.chamikathereal.schoolsystem.service.interfaces.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j; // Import Lombok Logger
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j // <--- 1. Adds 'log' object automatically
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public StudentResponseDto registerStudent(StudentDto request) {
        log.info("Attempting to register student with email: {}", request.getEmail()); // Log

        if (studentRepository.existsByEmail(request.getEmail())) {
            log.warn("Registration failed: Email {} already exists", request.getEmail()); // Warn Log
            throw new DuplicateResourceException("Email already taken!"); // Custom Exception
        }

        Student student = Student.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .mobile(request.getMobile())
                .address(request.getAddress())
                .dob(request.getDob())
                .build();

        Student savedStudent = studentRepository.save(student);
        log.info("Student registered successfully with ID: {}", savedStudent.getId()); // Success Log

        return mapToResponse(savedStudent);
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        log.info("Fetching all students");
        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponseDto getStudent(Long id) {
        log.info("Fetching student with ID: {}", id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Student with ID {} not found", id); // Error Log
                    return new ResourceNotFoundException("Student not found with ID: " + id); // Custom Exception
                });
        return mapToResponse(student);
    }

    @Override
    public StudentResponseDto updateStudent(Long id, StudentDto request) {
        log.info("Updating student with ID: {}", id);
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with ID: " + id));

        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setMobile(request.getMobile());
        student.setAddress(request.getAddress());
        student.setDob(request.getDob());

        Student updated = studentRepository.save(student);
        log.info("Student ID {} updated successfully", id);
        return mapToResponse(updated);
    }

    @Override
    public void deleteStudent(Long id) {
        log.warn("Deleting student with ID: {}", id);
        if (!studentRepository.existsById(id)) {
            log.error("Delete failed: Student ID {} not found", id);
            throw new ResourceNotFoundException("Student not found with ID: " + id);
        }
        studentRepository.deleteById(id);
        log.info("Student ID {} deleted", id);
    }

    private StudentResponseDto mapToResponse(Student student) {
        return StudentResponseDto.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .mobile(student.getMobile())
                .address(student.getAddress())
                .dob(student.getDob())
                .age(Period.between(student.getDob(), LocalDate.now()).getYears())
                .build();
    }
}