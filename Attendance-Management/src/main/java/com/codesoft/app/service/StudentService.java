package com.codesoft.app.service;

import com.codesoft.app.entity.Student;

import java.util.List;

public interface StudentService {

    Student create(Student student);

    Student update(Long id, Student student);

    Student getById(Long id);

    List<Student> list();   // you can later convert to paginated list

    List<Student> search(String keyword);
    // search by name, email, phone, batch, etc.

    void deactivate(Long id);
}
