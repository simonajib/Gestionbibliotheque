package com.example.gestionbibliotheque.service.Impl;


import com.example.gestionbibliotheque.Repository.StudentRepository;
import com.example.gestionbibliotheque.entities.Student;
import com.example.gestionbibliotheque.entities.Transaction;
import com.example.gestionbibliotheque.service.StudentService;
import com.example.gestionbibliotheque.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;
    private TransactionService transactionService;

    public StudentServiceImpl(StudentRepository studentRepository,TransactionService theTransactionService) {
        this.studentRepository= studentRepository;
        this.transactionService = theTransactionService;
    }

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findOne(Long studentId) {
        Optional<Student> student = studentRepository.findById(studentId);
        if (student.isPresent())
            return student.get();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found");
    }

    @Override
    public Student findStudentFromTransaction(Long id) {
        Transaction transaction = transactionService.findOne(id);
        Student student = (Student) this.findOne(transaction.getBorrower().getUid());

        return student;
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);

    }

    @Override
    public void delete(Long studentId) {
        if (studentRepository.findById(studentId).get()==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found");
        studentRepository.deleteById(studentId);
    }

}