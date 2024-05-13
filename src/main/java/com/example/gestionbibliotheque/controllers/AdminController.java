package com.example.gestionbibliotheque.controllers;

import com.example.gestionbibliotheque.entities.User;
import com.example.gestionbibliotheque.security.CurrentUserFinder;
import com.example.gestionbibliotheque.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@AllArgsConstructor


public class AdminController {

    @Autowired
    UserService userService;

    //affiche page d acceuil
    @GetMapping(value = "/admin")
    public String adminHome(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");
        if(currentUser != null && currentUser.getRole().equals("ADMIN")) {
            model.addAttribute("currentUser", currentUser);

            return "/Admin/HomeAdmin";
        } else {
            return "redirect:/login";
        }
    }
//affiche page d'utilisateurs
    @GetMapping(value="/admin/manageaccounts")
    public String manageAuthorities(HttpSession session,
                                    @RequestParam(required = false) String firstName,
                                    @RequestParam(required = false) String lastName,
                                    Model model) {
        User currentUser = (User) session.getAttribute("user");
        if(currentUser != null && currentUser.getRole().equals("ADMIN")) {
            List<User> users = userService.userSearcher(firstName, lastName);
            model.addAttribute("users", users);
            model.addAttribute("firstName", firstName);
            model.addAttribute("lastName", lastName);
            return "/Admin/ManageAccounts";
        } else {
            return "redirect:/login";
        }
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }


}