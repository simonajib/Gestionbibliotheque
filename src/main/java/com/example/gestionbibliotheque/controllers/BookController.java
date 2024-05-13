package com.example.gestionbibliotheque.controllers;

import com.example.gestionbibliotheque.entities.*;
import com.example.gestionbibliotheque.service.*;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller

public class BookController {

        @Autowired
        private ServletContext servletContext;

    private BookService bookService;
    private AuthorService authorService;
    private CategoryService categoryService;
    private NotificationService notificationService;
    private UserService userService;

    @Autowired
    private ReservationService reservationService;


    @Autowired
    public BookController(AuthorService authorService, CategoryService categoryService,UserService userService, BookService bookService,NotificationService notificationService) {
        this.authorService = authorService;
        this.categoryService = categoryService;
        this.userService = userService;
        this.bookService = bookService;
        this.notificationService = notificationService;
    }

    // expose "/books" and return list of books for librarian
    @GetMapping(value="/Books")
    public String listBooks( Model model) {

            List<Book> books = bookService.getAllBooks(); // Assuming you have a service method to get all books
            model.addAttribute("books", books);
            return "/Librarian/Book";

    }
    // expose "/books" and return list of books for student
    @GetMapping(value="/student/book")
    public String listStudentBooks(Model model) {

            List<Book> books = bookService.getAllBooks(); // Assuming you have a service method to get all books
            model.addAttribute("books", books);
            return "/Student/StudentBookList";

    }
    // delete book by id for librarian

    @GetMapping("/deleteBook/{isbn}")
    public String deleteBook(@PathVariable("isbn") Long isbn, RedirectAttributes redirectAttributes) {
        bookService.delete(isbn);
        redirectAttributes.addFlashAttribute("successMessage", "Book deleted successfully!");
        return "redirect:/books";
    }

    // Mapping to show add book form for librarian
    @GetMapping("/books/add")
    public String showAddBookForm(Model model) {
        List<Author> authors = authorService.getAllAuthors();
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("authors", authors);
        model.addAttribute("categories", categories);

        model.addAttribute("book", new Book());

        return "/Librarian/AddBook";
    }

    // Mapping to process add book form submission for librarian
    @PostMapping("/books/add")
    public String addBook(@ModelAttribute("book") Book book, @RequestParam("image") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                // Save the image file and set its path in the book object
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                String uploadDir = servletContext.getRealPath("/imagesroomType/");
                Image.saveImage(uploadDir, fileName, file);
                book.setImagePath("/imagesroomType/" + fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // Save the book object
        bookService.save(book);

        // Check if the added book should trigger a notification
        if (book.isAvailable() ) {
            // Get all users with role "STUDENT"
            List<User> students = userService.findUsersByRole("STUDENT");

            // Create a notification message
            String notificationMessage = "A new book titled '" + book.getTitle() + "' has been added.";

            // Send notification to each student
            for (User student : students) {
                Notification notification = Notification.builder()
                        .message(notificationMessage)
                        .createAt(new Date())
                        .recipient(student)
                        .build();
                notificationService.save(notification);
            }
        }

        return "redirect:/books";
    }
    //show you page for reserve the book
    @GetMapping("/reservation")
    public String showReserveForm(HttpSession session, Model model) {
        // Get the current user from the session
        User currentUser = (User) session.getAttribute("user");
        // Check if the user is logged in and has the role of a student
        if(currentUser != null && currentUser.getRole().equals("STUDENT")) {
            // If the user is a student, fetch all books
            List<Book> books = bookService.getAllBooks();
            // Add the list of books and a new Reservation object to the model
            model.addAttribute("books", books);
            model.addAttribute("reservation", new Reservation());
            // Return the view name for the reservation form for students
            return "/Student/Reserve";
        }else {
            // If the user is not logged in or is not a student, redirect to the login page
            return "redirect:/login";
        }
    }
    // Mapping to process add reservation form submission for student
    @PostMapping("/reservation")
    public String addReservation(@ModelAttribute("reservation") Reservation reservation, HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        reservation.setBorrower(currentUser); // Set the borrower
        reservation.setFulfilled(false); // Set as not fulfilled initially

        // Set the reservation date to the current date
        reservation.setDateOfReservation(new Date());
        reservationService.save(reservation); // Save the reservation
        // Update the availability of the reserved book
        Long reservedBookISBN = reservation.getBook().getIsbn();
        Book reservedBook = bookService.findOne(reservedBookISBN);
        if (reservedBook != null) {
            reservedBook.setAvailable(false); // Set availability to false
            reservedBook.setReservedByUser(currentUser); // Set the user who reserved the book

            bookService.save(reservedBook); // Save the book with updated availability
        }

        return "redirect:/student/books";
    }






}