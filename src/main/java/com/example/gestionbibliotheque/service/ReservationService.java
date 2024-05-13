package com.example.gestionbibliotheque.service;

import com.example.gestionbibliotheque.entities.Book;
import com.example.gestionbibliotheque.entities.Reservation;
import com.example.gestionbibliotheque.entities.User;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);

    Reservation updateReservation(Reservation reservation);

    void deleteReservationById(Long id);


    void deleteAllReservation();
    Reservation getReservationById(Long id);

    List<Reservation> getAllReservations();

    List<Reservation> getUnprocessedBookReservations();
    List<Reservation> getProcessedBookReservations();

    Reservation save(Reservation reservation);

    // Vous pouvez ajouter d'autres méthodes de service personnalisées selon les besoins
    void cancelReservation(Reservation reservation);

    Reservation findReservationByIsbn(Long isbn);

    List<Reservation> findReservationByUser(User user);

    int getUnfulfilledReservationCount();


    // Vous pouvez ajouter d'autres méthodes de service personnalisées selon les besoins




}