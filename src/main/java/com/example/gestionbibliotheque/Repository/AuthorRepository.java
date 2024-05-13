package com.example.gestionbibliotheque.Repository;

import com.example.gestionbibliotheque.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    // You can add custom query methods here if needed
    List<Author> findByNameContaining(String name);


}
