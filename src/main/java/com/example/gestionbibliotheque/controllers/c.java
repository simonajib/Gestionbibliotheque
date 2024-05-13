package com.example.gestionbibliotheque.controllers;

import com.example.gestionbibliotheque.entities.*;
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
public class c {
    @Autowired
    UserService userService;

    //affiche page d'utilisateurs
    @GetMapping(value="")
    public String manageAuthorities(HttpSession session)
                                   {
        User currentUser = (User) session.getAttribute("user");
        if(currentUser != null && currentUser.getRole().equals("LIBRARIAN")) {

            return "redirect:/librarian/manageaccount";
        } else {
            return "redirect:/login";
        }
    }
    //page d'acceuil librarian
    @GetMapping(value = "/librarian")
    public String librarianHome(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");
        if(currentUser != null && currentUser.getRole().equals("LIBRARIAN")) {



            return "redirect:/librarianhome";
        } else {
            return "redirect:/login";
        }
    }
    @GetMapping("/student/reserved-books")
    public String showReservedBooks(HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser != null && currentUser.getRole().equals("STUDENT")) {

            return "redirect:/student/reserved-book";
        } else {
            return "redirect:/login";
        }
    }
    @GetMapping("/librarian/reserved-books")
    public String ReservedBooks(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser.getRole().equals("LIBRARIAN")) {

            return "redirect:/librarian/reserved-book";
        } else {
            return "redirect:/login";
        }
    }
    @GetMapping("/addCategory")
    public String showAddCategoryForm(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");
        if (currentUser != null && currentUser.getRole().equals("LIBRARIAN")) {

            return "redirect:/addCategorys";
        } else {
            return "redirect:/login";
        }
    }
    @GetMapping(value="/Category")
    public String listcategory(HttpSession session
                              ) {
        User currentUser = (User) session.getAttribute("user");
        if(currentUser != null && currentUser.getRole().equals("LIBRARIAN")) {

            return "redirect:/Categorys";
        } else {
            return "redirect:/login";
        }
    }
    @GetMapping("/addAuthor")
    public String showAddAuthorForm(HttpSession session,
                                    Model model) {
        User currentUser = (User) session.getAttribute("user");
        if(currentUser != null && currentUser.getRole().equals("LIBRARIAN")) {
            model.addAttribute("author", new Author());
            return "redirect:/addauthor";}
        else {
            return "redirect:/login";
        }
    }

    //Afficher tous les auteurs
    @GetMapping(value="/Authors")
    public String listauthor(HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if(currentUser != null && currentUser.getRole().equals("LIBRARIAN")) {

            return "redirect:/Author";
        } else {
            return "redirect:/login";
        }
    }
    @GetMapping(value="/books")
    public String listBooks(HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if(currentUser != null && currentUser.getRole().equals("LIBRARIAN")) {

            return "redirect:/Books";
        } else {
            return "redirect:/login";
        }
    }
    @GetMapping(value="/student/books")
    public String listStudentBooks(HttpSession session) {
        User currentUser = (User) session.getAttribute("user");
        if(currentUser != null && currentUser.getRole().equals("STUDENT")) {


            return "redirect:/student/book";
        } else {
            return "redirect:/login";
        }
    }

}
