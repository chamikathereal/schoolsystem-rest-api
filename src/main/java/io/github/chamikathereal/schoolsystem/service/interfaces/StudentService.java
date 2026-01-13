package io.github.chamikathereal.schoolsystem.service.interfaces;

import io.github.chamikathereal.schoolsystem.dto.request.StudentDto;
import io.github.chamikathereal.schoolsystem.dto.response.StudentResponseDto;
import java.util.List;

public interface StudentService {
    StudentResponseDto registerStudent(StudentDto request);
    List<StudentResponseDto> getAllStudents();
    StudentResponseDto getStudent(Long id);
    StudentResponseDto updateStudent(Long id, StudentDto request);
    void deleteStudent(Long id);
}