package com.example.gestionbibliotheque.controllers;

import com.example.gestionbibliotheque.entities.Author;
import com.example.gestionbibliotheque.entities.User;
import com.example.gestionbibliotheque.service.AuthorService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@AllArgsConstructor
public class AuthorController {
    @Autowired
    AuthorService authorService;

    // Afficher le formulaire pour ajouter un auteur
    @GetMapping("/addauthor")
    public String showAddAuthorForm(
                                    Model model) {

        model.addAttribute("author", new Author());
        return "/Librarian/AddAuthor";

    }
    // Ajouter un auteur
    @PostMapping("/addAuthor")
    public String addAuthor(@ModelAttribute("author") Author author, BindingResult result) {
        if (result.hasErrors()) {
            return "AddAuthor";
        }

        authorService.createAuthor(author);
        return "redirect:/Authors";
    }

    //Afficher tous les auteurs
    @GetMapping(value="/Author")
    public String listauthor(@RequestParam(required = false) String name,
                             Model model) {

            List<Author> authors = authorService.authorSearcher(name);
            model.addAttribute("authors", authors);
            return "/Librarian/AuthorList";

    }
    //supprimer un auteur par id
    @GetMapping("/deleteAuthor/{id}")
    public String deleteAuthor(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        authorService.deleteAuthorById(id);
        redirectAttributes.addFlashAttribute("successMessage", "Author deleted successfully!");
        return "redirect:/Authors";
    }
}
