package com.example.gestionbibliotheque.service;

import com.example.gestionbibliotheque.entities.Notification;
import com.example.gestionbibliotheque.entities.User;

import java.util.List;

public interface NotificationService {

    public List<Notification> findAll();

    public Notification findOne(Long id);

    public void save(Notification notification);

    public void delete(Long id);


    void sendReturnNotifications();
}