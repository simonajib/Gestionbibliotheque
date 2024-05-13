package com.example.gestionbibliotheque.controllers;

import com.example.gestionbibliotheque.entities.Transaction;
import com.example.gestionbibliotheque.entities.User;
import com.example.gestionbibliotheque.service.BookService;
import com.example.gestionbibliotheque.service.ReservationService;
import com.example.gestionbibliotheque.service.TransactionService;
import com.example.gestionbibliotheque.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller

public class TransactionController {

    private final BookService bookService;
    private final ReservationService reservationService;
    private final TransactionService transactionService;
    private final UserService userService;

    @Autowired
    public TransactionController(BookService bookService,ReservationService reservationService, TransactionService transactionService,UserService userService) {
        this.bookService = bookService;
        this.reservationService = reservationService;
        this.transactionService = transactionService;
        this.userService = userService;
    }
    // Shows a list of all transactions for the librarian.
    @GetMapping("/librarian/transactions")
    public String showTransactions(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser.getRole().equals("LIBRARIAN")) {
        List<Transaction> transactions = transactionService.getAllTransactions();
        model.addAttribute("transactions", transactions);
        return "/Librarian/Transaction"; // Assuming you have a view named "TransactionList"
        } else {
            return "redirect:/login";
        }
    }
    // Handles the return of a book by a librarian.
    @PostMapping("/librarian/transaction/return")
    public String returnBook(
            @RequestParam Long transactionId,
            RedirectAttributes redirectAttributes
    ) {
        Transaction transaction = transactionService.findOne(transactionId);
        if (transaction != null) {
            // Mark the transaction as returned
            transaction.setReturned(true);
            transactionService.updateTransaction(transaction);
            redirectAttributes.addFlashAttribute("successMessage", "Book returned successfully.");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "Transaction not found.");
        }
        return "redirect:/librarian/transactions";
    }

    // Shows a list of books borrowed by the current student.
    @GetMapping("/student/borrowed-books")
    public String showBorrowedBooks(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser != null && currentUser.getRole().equals("STUDENT")) {
            List<Transaction> transactions = transactionService.findBorrowedBooksByUid(currentUser.getUid());
            model.addAttribute("transactions", transactions);
            return "/Student/BorrowedBooks"; // Assuming you have a JSP page named BorrowedBooks.jsp
        } else {
            return "redirect:/login";
        }
    }

}
