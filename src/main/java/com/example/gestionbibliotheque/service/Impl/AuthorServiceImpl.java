package com.example.gestionbibliotheque.service.Impl;

import com.example.gestionbibliotheque.Repository.AuthorRepository;
import com.example.gestionbibliotheque.entities.Author;
import com.example.gestionbibliotheque.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service

public class AuthorServiceImpl implements AuthorService {


    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    @Override
    public Author createAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }


    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).get();
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }



    @Override
    public List<Author> authorSearcher(String name) {
        if (name != null && !name.isEmpty()) {
            return authorRepository.findByNameContaining(name);
        } else {
            return authorRepository.findAll();
        }
    }
}