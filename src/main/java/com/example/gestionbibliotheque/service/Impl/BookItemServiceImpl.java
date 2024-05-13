package com.example.gestionbibliotheque.service.Impl;

import com.example.gestionbibliotheque.Repository.BookItemRepository;
import com.example.gestionbibliotheque.entities.BookItem;
import com.example.gestionbibliotheque.service.BookItemService;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookItemServiceImpl implements BookItemService {

    private BookItemRepository bookItemRepository;

    public BookItemServiceImpl(BookItemRepository theBookItemRepository) {
        bookItemRepository = theBookItemRepository;
    }

    @Override
    public List<BookItem> findAll() {
        return bookItemRepository.findAll();
    }

    @Override
    public BookItem findOne(Long barcode) {
        Optional<BookItem> bookItem = bookItemRepository.findById(barcode);
        if (bookItem.isPresent())
            return bookItem.get();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Did not find bookItem barcode - " + barcode);
    }

    @Override
    public void save(BookItem bookItem) {
        bookItemRepository.save(bookItem);

    }

    @Override
    public void delete(Long barcode) {
        if (bookItemRepository.findById(barcode).get()== null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "BookItem not found");
        bookItemRepository.deleteById(barcode);
    }

}