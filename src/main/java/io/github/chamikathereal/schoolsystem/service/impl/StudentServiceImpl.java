package io.github.chamikathereal.schoolsystem.service.impl;

import io.github.chamikathereal.schoolsystem.dto.request.StudentDto;
import io.github.chamikathereal.schoolsystem.dto.response.StudentResponseDto;
import io.github.chamikathereal.schoolsystem.entity.Student;
import io.github.chamikathereal.schoolsystem.repository.StudentRepository;
import io.github.chamikathereal.schoolsystem.service.interfaces.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service // Tells Spring: "This is the bean you should inject when someone asks for StudentService"
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public StudentResponseDto registerStudent(StudentDto request) {
        if (studentRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already taken!");
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
        return mapToResponse(savedStudent);
    }

    @Override
    public List<StudentResponseDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public StudentResponseDto getStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        return mapToResponse(student);
    }

    @Override
    public StudentResponseDto updateStudent(Long id, StudentDto request) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        student.setFirstName(request.getFirstName());
        student.setLastName(request.getLastName());
        student.setMobile(request.getMobile());
        student.setAddress(request.getAddress());
        student.setDob(request.getDob());

        return mapToResponse(studentRepository.save(student));
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found");
        }
        studentRepository.deleteById(id);
    }

    // Helper method (private, so not in the interface)
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