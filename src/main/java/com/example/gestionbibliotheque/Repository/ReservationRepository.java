package com.example.gestionbibliotheque.Repository;

import com.example.gestionbibliotheque.entities.Reservation;
import com.example.gestionbibliotheque.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByFulfilledFalse();

    List<Reservation> findByFulfilledTrue();

    Reservation findByBook_Isbn(Long isbn);

    List<Reservation> findByBorrower(User borrower);

    // Vous pouvez ajouter des méthodes personnalisées de requête si nécessaire
    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.fulfilled = false")
    int countUnfulfilledReservations();

}
