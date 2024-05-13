package com.example.gestionbibliotheque.controllers;

import com.example.gestionbibliotheque.entities.Author;
import com.example.gestionbibliotheque.entities.Notification;
import com.example.gestionbibliotheque.entities.User;
import com.example.gestionbibliotheque.service.NotificationService;
import com.example.gestionbibliotheque.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class StudentController {


    @Autowired
    UserService userService;
    @Autowired
    NotificationService notificationService;

//page d'acceuil  student

    @GetMapping(value = "/student")
    public String studentHome(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("user");
        if(currentUser != null && currentUser.getRole().equals("STUDENT")) {
            List<Notification> notifications = notificationService.findAll();
            model.addAttribute("currentUser", currentUser);
            model.addAttribute("notifications", notifications);

            return "/Student/HomeStudent";
        } else {
            return "redirect:/login";
        }
    }

}
