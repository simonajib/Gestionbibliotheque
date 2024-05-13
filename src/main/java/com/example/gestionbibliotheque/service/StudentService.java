package com.example.gestionbibliotheque.service;

import com.example.gestionbibliotheque.entities.Student;

import java.util.List;

public interface StudentService {
    public List<Student> findAll();

    public Student findOne(Long uid);

    public void save(Student student);

    public void delete(Long uid);

    Student findStudentFromTransaction(Long id);
}