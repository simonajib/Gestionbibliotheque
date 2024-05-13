package com.example.gestionbibliotheque.service.Impl;

import com.example.gestionbibliotheque.Repository.BookRepository;
import com.example.gestionbibliotheque.Repository.TransactionRepository;
import com.example.gestionbibliotheque.Repository.UserRepository;
import com.example.gestionbibliotheque.entities.*;
import com.example.gestionbibliotheque.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


@Service
public class BookServiceImpl implements BookService {
    private final Path rootLocation = Paths.get("C:/xampp/htdocs/img");
    private BookRepository bookRepository;
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public BookServiceImpl(BookRepository theBookRepository, TransactionRepository theTransactionRepository) {
        bookRepository = theBookRepository;
        transactionRepository = theTransactionRepository;

    }
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }


    @Override
    public List<Book> findAll() {
        return null;
    }

    // DONT FORGET TO LOOKUP HOW TO HANDLE EXCEPTIONS
    @Override
    public Book findOne(Long isbn) {
        Optional<Book> book = bookRepository.findById(isbn);
        if (book.isPresent())
            return book.get();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book Not Found");
    }

    @Override
    public List<Book> findBookByCategory(Category category){
        List<Book> books = bookRepository.findByCategory(category);
        return books;
    }



    @Override
    public Book getByTitleAndAuthor(String title, Author author) {
        return bookRepository.findByTitleAndAuthor(title, author)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book Not Found"));
    }



    @Override
    public int checkAndUpdateBookAvailability(Long isbn) {
        Book book = this.findOne(isbn);
        int bookItemsNumber = book.getBookItems().size();
        int bookIssued=0;
        List<Transaction> transaction = transactionRepository.findByBook(book);
        for (Transaction transactionTemp : transaction) {
            if (!transactionTemp.isReturned())
                bookIssued++;
        }
        if (bookIssued<bookItemsNumber)
        {
            if (!book.isAvailable()){
                book.setAvailable(true);
                this.save(book);
            }
        }
        else {
            if (book.isAvailable())
            {
                book.setAvailable(false);
                this.save(book);
            }
        }
        return bookItemsNumber-bookIssued;
    }

    @Override
    public Book getByTitle(String title) {
        return null;
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
        //book.getBookItems( ) ==0
    }

    @Override
    public void saveImage(MultipartFile image) throws IOException {

        Files.copy(image.getInputStream(), this.rootLocation.resolve(image.getOriginalFilename()));

    }

    @Override
    public void delete(Long isbn) {
        if (bookRepository.findById(isbn).get()==null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book Not Found");
        bookRepository.deleteById(isbn);
    }


    @Override
    public List<Book> findReservedBooksByUser(User user) {
        return bookRepository.findByReservedByUser(user);
    }

    @Override
    public void updateBook(Book book) {
        bookRepository.save(book);
    }



}