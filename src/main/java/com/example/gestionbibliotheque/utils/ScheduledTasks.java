package com.example.gestionbibliotheque.utils;

import com.example.gestionbibliotheque.entities.Transaction;
import com.example.gestionbibliotheque.entities.User;
import com.example.gestionbibliotheque.service.NotificationService;
import com.example.gestionbibliotheque.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ScheduledTasks {

    private final NotificationService notificationService;

    public ScheduledTasks(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Schedule the notification sending task to run once a day
    @Scheduled(cron = "0 0 0 * * *")
    public void sendReturnNotifications() {
        notificationService.sendReturnNotifications();
    }
}
