package com.example.gestionbibliotheque.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "reservation")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_reservation")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfReservation;

    @Temporal(TemporalType.DATE)
    @Column(name = "pickup_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date pickupDate;

    @ManyToOne
    private Book book;

    @ManyToOne
    private User borrower;

    private boolean fulfilled =false;

    @Override
    public String toString() {
        return "Reservation [id=" + id + ", dateOfReservation=" + dateOfReservation + ", pickupDate=" + pickupDate +
                ", book=" + book + ", borrower=" + borrower + "]";
    }
}