package com.example.gestionbibliotheque.Repository;

import com.example.gestionbibliotheque.entities.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

    public interface LibrarianRepository extends JpaRepository<Librarian, Long> {

}