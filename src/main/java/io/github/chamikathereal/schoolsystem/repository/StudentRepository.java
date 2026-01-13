package io.github.chamikathereal.schoolsystem.repository;

import io.github.chamikathereal.schoolsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // Custom query to check for duplicates
    // Spring generates: SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false FROM Student s WHERE s.email = ?1
    boolean existsByEmail(String email);
}