package com.example.gestionbibliotheque.controllers;

import com.example.gestionbibliotheque.entities.*;
import com.example.gestionbibliotheque.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller

public class ReservationController {
    private final BookService bookService;
    private final ReservationService reservationService;
    private final TransactionService transactionService;
    private final UserService userService;

    @Autowired
    public ReservationController(BookService bookService,ReservationService reservationService, TransactionService transactionService,UserService userService) {
        this.bookService = bookService;
        this.reservationService = reservationService;
        this.transactionService = transactionService;
        this.userService = userService;
    }
    //show the books that are reserved by the student
    @GetMapping("/student/reserved-book")
    public String showReservedBooks(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");

            List<Reservation> reservations = reservationService.findReservationByUser(currentUser);
            model.addAttribute("reservations", reservations);
            return "/Student/ReservedBooks";

    }
    @PostMapping("/reservations/cancel")
    public String cancelReservation(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        Long reservationId = Long.parseLong(request.getParameter("reservationId"));
        //cancel a reservation

        // Find the reservation to cancel
        Reservation reservation = reservationService.getReservationById(reservationId);

        if (reservation != null) {
            // Update the availability of the reserved book
            Book reservedBook = reservation.getBook();
            if (reservedBook != null) {
                reservedBook.setAvailable(true); // Set availability to true
                reservedBook.setReservedByUser(null); // Remove the user who reserved the book
                bookService.save(reservedBook); // Save the book with updated availability
            }

            // Delete the reservation
            reservationService.deleteReservationById(reservationId);

            redirectAttributes.addFlashAttribute("successMessage", "Reservation canceled successfully.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Reservation not found.");
        }

        return "redirect:/student/reserved-books";
    }
    // Shows the list of books that have been reserved by users.
    @GetMapping("/librarian/reserved-book")
    public String ReservedBooks(Model model) {

            List<Reservation> reservedBooks = reservationService.getAllReservations();
            model.addAttribute("reservedBooks", reservedBooks);
            return "/Librarian/ReserveBookList";

    }
    //confirmation of reservation
    @PostMapping("/librarian/reservation/update")
    public String updateReservation(
            @RequestParam Long reservationId,
            @RequestParam Long isbn,
            @RequestParam Long uid,
            @RequestParam boolean fulfilled,
            RedirectAttributes redirectAttributes
    ) {
        Reservation reservation = reservationService.getReservationById(reservationId);
        Book book = bookService.findOne(isbn);
        if (reservation != null && book != null) {
            // Update the book's status to ready for pickup
            book.setReadyForPickUp(true);

            // Update the reservation
            reservation.setFulfilled(fulfilled);
            reservation.setPickupDate(new Date());

            // Save the changes
            bookService.updateBook(book);
            reservationService.updateReservation(reservation);


            User borrower = reservation.getBorrower();

            // Calculate the return date (21 days from the issue date)
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE, 21);
            Date returnDate = calendar.getTime();

            // Create and save the transaction
            Transaction transaction = Transaction.builder()
                    .dateOfIssue(new Date())
                    .returnDate(returnDate)
                    .book(book)
                    .borrower((User) borrower)
                    .returned(false)
                    .build();
            transactionService.saveTransaction(transaction);

            redirectAttributes.addFlashAttribute("successMessage", "Reservation updated successfully.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Reservation or book not found.");
        }
        return "redirect:/librarian/reserved-books";
    }




}