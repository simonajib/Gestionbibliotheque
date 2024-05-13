package com.example.gestionbibliotheque.service.Impl;

import com.example.gestionbibliotheque.Repository.BookRepository;
import com.example.gestionbibliotheque.Repository.ReservationRepository;
import com.example.gestionbibliotheque.entities.Reservation;
import com.example.gestionbibliotheque.entities.User;
import com.example.gestionbibliotheque.service.BookService;
import com.example.gestionbibliotheque.service.ReservationService;
import com.example.gestionbibliotheque.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
@Autowired
    private ReservationRepository reservationRepository;
    BookRepository bookRepository;


    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;


    @Override
    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public Reservation updateReservation(Reservation reservation) {
        // Retrieve the existing reservation from the database
        Reservation existingReservation = reservationRepository.findById(reservation.getId())
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        // Update the existing reservation with the new values
        existingReservation.setFulfilled(reservation.isFulfilled());
        existingReservation.setPickupDate(new Date()); // Set pickup date to current date

        // Save the updated reservation
        return reservationRepository.save(existingReservation);
    }

    @Override
    public void deleteReservationById(Long id) {
        reservationRepository.deleteById(id);
    }

    @Override
    public void  deleteAllReservation() {
        reservationRepository.deleteAll();

    }

    @Override
    public Reservation getReservationById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
    @Override
    public List<Reservation> getUnprocessedBookReservations() {
        return reservationRepository.findByFulfilledFalse();
    }

    @Override
    public List<Reservation> getProcessedBookReservations() {
        return reservationRepository.findByFulfilledTrue();
    }


@Override
    public Reservation save(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    @Override
    public void cancelReservation(Reservation reservation) {

    }
// Vous pouvez ajouter d'autres méthodes de service personnalisées selon les besoins


    public Reservation findReservationByIsbn(Long isbn) {
        return reservationRepository.findByBook_Isbn(isbn);
    }
    @Override
    public List<Reservation> findReservationByUser(User user) {
        return reservationRepository.findByBorrower(user);
    }
    @Override
    public int getUnfulfilledReservationCount() {
        return reservationRepository.countUnfulfilledReservations();
    }

}