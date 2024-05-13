package com.example.gestionbibliotheque.controllers;

import com.example.gestionbibliotheque.entities.User;
import com.example.gestionbibliotheque.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class SecurityController {


    @Autowired
    UserService accService;


//affichage de page de login
@GetMapping(value = {"/", "/login"})
    public String login(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid username or password. Please try again.");
        }
        return "Login";
    }
//affichage de page de register
    @GetMapping(value = "/register")
    public String register(Model model) {
        model.addAttribute("newAccount", new User());
        return "CreateUser";
    }
//ajouter un utilisteur
    @PostMapping(value = "/register/save")
    public String saveNewAccount(User account) {
        account.setPassword(account.getPassword());
        accService.saveUser(account);
        return "redirect:/register/accountcreated";
    }
//page de confirmation
    @GetMapping(value = "/register/accountcreated")
    public String accountCreated() {
        return "account-created";
    }
//permet de login
    @PostMapping("/login")
    public String login(@RequestParam String login, @RequestParam String password, HttpSession session) {
        User user = accService.findByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            if (user.getRole().equals("LIBRARIAN")) {
                    return "redirect:/librarianhome";// Redirect to admin home page
            } else if (user.getRole().equals("STUDENT")) {
                return "redirect:/student"; // Redirect to student home page
            } else if (user.getRole().equals("ADMIN")) {
                return "redirect:/admin";  // Redirect to librarian home page
            }
        }
        return "redirect:/login?error"; // Redirect to login page with error
    }



}