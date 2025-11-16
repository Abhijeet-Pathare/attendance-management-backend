package com.codesoft.app.repository;

import com.codesoft.app.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // Search by name, email, phone
    List<Student> findByNameContainingIgnoreCase(String name);

    List<Student> findByEmailContainingIgnoreCase(String email);

    List<Student> findByPhoneContaining(String phone);

    // Find by batchId
    List<Student> findByBatchId(Long batchId);

    // Find by status
    List<Student> findByStatus(String status);
}
