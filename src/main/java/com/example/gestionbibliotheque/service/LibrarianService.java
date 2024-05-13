package com.example.gestionbibliotheque.service;

import com.example.gestionbibliotheque.entities.Librarian;

import java.util.List;

public interface LibrarianService {

    public List<Librarian> findAll();

    public Librarian findOne(Long uid);

    public void save(Librarian librarian);

    public void delete(Long uid);
}