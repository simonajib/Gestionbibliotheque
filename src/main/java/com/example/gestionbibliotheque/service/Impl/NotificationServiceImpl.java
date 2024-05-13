package com.example.gestionbibliotheque.service.Impl;

import com.example.gestionbibliotheque.Repository.NotificationRepository;
import com.example.gestionbibliotheque.Repository.TransactionRepository;
import com.example.gestionbibliotheque.Repository.UserRepository;
import com.example.gestionbibliotheque.entities.Notification;
import com.example.gestionbibliotheque.entities.Transaction;
import com.example.gestionbibliotheque.entities.User;
import com.example.gestionbibliotheque.service.NotificationService;
import com.example.gestionbibliotheque.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements NotificationService {
    private NotificationRepository notificationRepository;
    private TransactionRepository transactionRepository;
    private UserRepository userRepository;

    @Autowired
    public NotificationServiceImpl(NotificationRepository theNotificationRepository, TransactionRepository transactionRepository, UserRepository userRepository) {
        notificationRepository = theNotificationRepository;
        this.transactionRepository = transactionRepository;
        this.userRepository= userRepository;
    }

    @Override
    public List<Notification> findAll() {
        return notificationRepository.findAll();
    }

    // DONT FORGET TO LOOKUP HOW TO HANDLE EXCEPTIONS
    @Override
    public Notification findOne(Long id) {
        Optional<Notification> notification = notificationRepository.findById(id);
        if (notification.isPresent())
            return notification.get();
        else
            throw new RuntimeException("Did not find notification id - " + id);
    }

    @Override
    public void save(Notification notification) {
        notificationRepository.save(notification);
    }

    @Override
    public void delete(Long id) {
        notificationRepository.deleteById(id);

    }



    public void sendReturnNotifications() {
        // Retrieve overdue transactions
        List<Transaction> overdueTransactions = transactionRepository.findOverdueTransactions();

        // Iterate over overdue transactions
        for (Transaction transaction : overdueTransactions) {
            // Check if the borrower has not returned the book
            if (!transaction.isReturned()) {
                // Create a notification for the borrower
                Notification notification = createNotification(transaction);

                // Save the notification to the database
                notificationRepository.save(notification);
            }
        }
    }

    private Notification createNotification(Transaction transaction) {
        // Create a notification message
        String message = "Please return the book  " + transaction.getBook().getTitle() ;

        // Create a new notification object
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setCreateAt(new Date());
        notification.setRecipient(transaction.getBorrower());

        return notification;
    }
}