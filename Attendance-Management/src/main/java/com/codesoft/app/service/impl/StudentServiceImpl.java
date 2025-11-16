package com.codesoft.app.service.impl;

import com.codesoft.app.entity.Student;
import com.codesoft.app.repository.StudentRepository;
import com.codesoft.app.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student create(Student student) {
        student.setStatus("ACTIVE"); // default
        return studentRepository.save(student);
    }

    @Override
    public Student update(Long id, Student student) {
        Optional<Student> optional = studentRepository.findById(id);
        if (!optional.isPresent()) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        Student existing = optional.get();
        existing.setName(student.getName());
        existing.setEmail(student.getEmail());
        existing.setPhone(student.getPhone());
        existing.setParentPhone(student.getParentPhone());
        existing.setStandard(student.getStandard());
        existing.setBatchId(student.getBatchId());
        existing.setStatus(student.getStatus());
        return studentRepository.save(existing);
    }

    @Override
    public Student getById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    @Override
    public List<Student> list() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> search(String keyword) {
        List<Student> byName = studentRepository.findByNameContainingIgnoreCase(keyword);
        if (!byName.isEmpty()) return byName;
        List<Student> byEmail = studentRepository.findByEmailContainingIgnoreCase(keyword);
        if (!byEmail.isEmpty()) return byEmail;
        List<Student> byPhone = studentRepository.findByPhoneContaining(keyword);
        return byPhone;
    }

    @Override
    public void deactivate(Long id) {
        Student student = getById(id);
        student.setStatus("INACTIVE");
        studentRepository.save(student);
    }
}
