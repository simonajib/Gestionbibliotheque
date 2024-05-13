package com.example.gestionbibliotheque.Repository;

import com.example.gestionbibliotheque.entities.Author;
import com.example.gestionbibliotheque.entities.Book;
import com.example.gestionbibliotheque.entities.Category;
import com.example.gestionbibliotheque.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface BookRepository extends JpaRepository<Book, Long> {


    List<Book> findByCategory(Category category);



    List<Book> findAll();

    Book getByTitleAndAuthor(String title, Author author);

    Optional<Book> findByTitleAndAuthor(String title, Author author);


    List<Book> findByReservedByUser(User user);
}