package com.example.gestionbibliotheque.utils;

import com.example.gestionbibliotheque.Repository.AuthorRepository;
import com.example.gestionbibliotheque.Repository.BookRepository;
import com.example.gestionbibliotheque.Repository.CategoryRepository;
import com.example.gestionbibliotheque.Repository.UserRepository;
import com.example.gestionbibliotheque.entities.Author;
import com.example.gestionbibliotheque.entities.Book;
import com.example.gestionbibliotheque.entities.Category;
import com.example.gestionbibliotheque.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public DatabaseLoader(UserRepository userRepository,BookRepository bookRepository, AuthorRepository authorRepository,CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;

    }


    @Override
    public void run(String... args) throws Exception {
        // Create a new user
        User user = User.builder()
                .login("Najib")
                .password("12345")
                .firstName("Mohamed")
                .lastName("Najib")
                .email("najibmohamed@example.com")
                .phoneNumber(123456789)
                .role("STUDENT")
                .build();

        // Check if the user already exists
        User existingUser = userRepository.findByLogin(user.getLogin());
        if (existingUser == null) {
            // Save the user to the database
            userRepository.save(user);
        }

        // Create an ADMIN user
        User adminUser = User.builder()
                .login("admin")
                .password("12345")
                .firstName("Admin")
                .lastName("User")
                .email("admin@gmail.com")
                .phoneNumber(659556270)
                .role("ADMIN")
                .build();

        // Check if the ADMIN user already exists
        existingUser = userRepository.findByLogin(adminUser.getLogin());
        if (existingUser == null) {
            // Save the ADMIN user to the database
            userRepository.save(adminUser);
        }

        // Create a LIBRARIAN user
        User librarianUser = User.builder()
                .login("mouad")
                .password("12345")
                .firstName("Mouad")
                .lastName("Arahal")
                .email("mouad@gmail.com")
                .phoneNumber(649545680)
                .role("LIBRARIAN")
                .build();

        // Check if the LIBRARIAN user already exists
        existingUser = userRepository.findByLogin(librarianUser.getLogin());
        if (existingUser == null) {
            // Save the LIBRARIAN user to the database
            userRepository.save(librarianUser);
        }

            // Create and save authors
            Author author1 = Author.builder().name("Ahmed Sefrioui").build();
            authorRepository.save(author1);

            Category category1 = Category.builder().category("Roman autobiographique").build();
            categoryRepository.save(category1);
        // Create a book
        Book book = Book.builder()
                .isbn(1158226L)
                .title("La boite à merveilles")
                .description("Autobiographie d`Ahmed Sefrioui")
                .author(author1)
                .edition("Du Seuil")
                .publisher("Revue mondiale")
                .numberOfPages(249)
                .language("Francais")
                .imagePath("/imagesroomType/maxresdefault.jpg")
                .available(true)
                .category(category1)
                .build();

        // Save the book to the database
        bookRepository.save(book);

    }
}
