package com.example.gestionbibliotheque.service;

import com.example.gestionbibliotheque.entities.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    // Method to create a new author
    Author createAuthor(Author author);

    // Method to update an existing author
    Author updateAuthor(Author author);

    // Method to delete an author by ID

    void deleteAuthorById(Long id);


    List<Author> authorSearcher(String name);


    // Method to find an author by ID
    Author getAuthorById(Long id);

    // Method to find all authors
    List<Author> getAllAuthors();


}