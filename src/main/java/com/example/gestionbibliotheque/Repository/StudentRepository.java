package com.example.gestionbibliotheque.Repository;

import com.example.gestionbibliotheque.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface StudentRepository extends JpaRepository<Student, Long> {


}