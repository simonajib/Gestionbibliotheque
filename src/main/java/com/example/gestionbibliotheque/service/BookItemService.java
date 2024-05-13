package com.example.gestionbibliotheque.service;

import com.example.gestionbibliotheque.entities.BookItem;

import java.util.List;

public interface BookItemService {
    public List<BookItem> findAll();

    public BookItem findOne(Long barcode);

    public void save(BookItem bookItem);

    public void delete(Long barcode);
}