package com.example.gestionbibliotheque.service;

import com.example.gestionbibliotheque.entities.Author;
import com.example.gestionbibliotheque.entities.Book;
import com.example.gestionbibliotheque.entities.Category;
import com.example.gestionbibliotheque.entities.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService {

    public List<Book> findAll();

    public Book findOne(Long isbn);

    public void save(Book book);

    public void delete(Long isbn);

    int checkAndUpdateBookAvailability(Long isbn);


    Book getByTitle(String title);

    List<Book> findBookByCategory(Category category);

    void saveImage(MultipartFile image) throws Exception;


    Book getByTitleAndAuthor(String title, Author author);

    List<Book> getAllBooks();

    List<Book> findReservedBooksByUser(User user);


    void updateBook(Book book);


}