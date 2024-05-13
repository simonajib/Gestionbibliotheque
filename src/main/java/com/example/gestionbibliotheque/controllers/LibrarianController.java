package com.example.gestionbibliotheque.controllers;

import com.example.gestionbibliotheque.entities.User;
import com.example.gestionbibliotheque.service.BookService;
import com.example.gestionbibliotheque.service.ReservationService;
import com.example.gestionbibliotheque.service.TransactionService;
import com.example.gestionbibliotheque.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class LibrarianController {

    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;
    @Autowired
    ReservationService reservationService;
    @Autowired
    TransactionService transactionService;
//page d'acceuil librarian
    @GetMapping(value = "/librarianhome")
    public String librarianHome(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");

            model.addAttribute("currentUser", currentUser);
            int studentCount = userService.countUsersByRole("STUDENT");
            model.addAttribute("studentCount", studentCount);

            // Get number of books
            int bookCount = bookService.getAllBooks().size();
            model.addAttribute("bookCount", bookCount);

            // Get number of fulfilled reservations
            int fulfilledReservationCount = reservationService.getUnfulfilledReservationCount();
            model.addAttribute("fulfilledReservationCount", fulfilledReservationCount);

            // Get number of transactions not returned
            int unreturnedTransactionCount = transactionService.getUnreturnedTransactionCount();
            model.addAttribute("unreturnedTransactionCount", unreturnedTransactionCount);


            return "/Librarian/HomeLibrarian";

    }
    //affiche page d'utilisateurs
    @GetMapping(value="/librarian/manageaccounts")
    public String manageAuthorities(
                                    @RequestParam(required = false) String firstName,
                                    @RequestParam(required = false) String lastName,
                                    Model model) {

            List<User> users = userService.userSearcher(firstName, lastName);
            model.addAttribute("users", users);
            model.addAttribute("firstName", firstName);
            model.addAttribute("lastName", lastName);
            return "/Librarian/LibrarianAccounts";

    }

}
