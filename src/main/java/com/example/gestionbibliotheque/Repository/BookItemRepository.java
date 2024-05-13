package com.example.gestionbibliotheque.Repository;

import com.example.gestionbibliotheque.entities.BookItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookItemRepository extends JpaRepository<BookItem, Long> {

}