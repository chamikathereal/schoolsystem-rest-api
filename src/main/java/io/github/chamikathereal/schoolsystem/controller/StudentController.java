package io.github.chamikathereal.schoolsystem.controller;

import io.github.chamikathereal.schoolsystem.dto.request.StudentDto;
import io.github.chamikathereal.schoolsystem.dto.response.StudentResponseDto;
import io.github.chamikathereal.schoolsystem.service.interfaces.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    // 1. Register: Principal and Clerk only
    @PostMapping
    @PreAuthorize("hasAnyAuthority('PRINCIPAL', 'CLERK')")
    public ResponseEntity<StudentResponseDto> registerStudent(@RequestBody StudentDto request) {
        return ResponseEntity.ok(studentService.registerStudent(request));
    }

    // 2. View All: Everyone (Principal, Clerk, Teacher)
    @GetMapping
    @PreAuthorize("hasAnyAuthority('PRINCIPAL', 'CLERK', 'TEACHER')")
    public ResponseEntity<List<StudentResponseDto>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    // 3. View One: Everyone
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('PRINCIPAL', 'CLERK', 'TEACHER')")
    public ResponseEntity<StudentResponseDto> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getStudent(id));
    }

    // 4. Update: Principal Only (As per your requirement "Top level can only... update")
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('PRINCIPAL')")
    public ResponseEntity<StudentResponseDto> updateStudent(@PathVariable Long id, @RequestBody StudentDto request) {
        return ResponseEntity.ok(studentService.updateStudent(id, request));
    }

    // 5. Delete: Principal and Clerk
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('PRINCIPAL', 'CLERK')")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    }
}