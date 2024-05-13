package com.example.gestionbibliotheque.controllers;

import com.example.gestionbibliotheque.entities.Notification;
import com.example.gestionbibliotheque.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class NotificationController {
    @Autowired
    NotificationService notificationService;
    @PostMapping("/deleteNotification")
    public String deleteNotification(@RequestParam("notificationId") Long notificationId) {
        // Retrieve the notification by its ID
        Notification notification = notificationService.findOne(notificationId);

        // Delete the notification if found
        if (notification != null) {
            notificationService.delete(notification.getId());
        } else {
            // Handle the case where the notification is not found
        }

        // Redirect back to the page where notifications are displayed
        return "redirect:/student";
    }
}
