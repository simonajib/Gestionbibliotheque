package com.example.gestionbibliotheque.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "Student")
@DiscriminatorValue(value = "Student")

@Data

public class Student extends User{



    @JsonManagedReference
    @OneToMany(mappedBy = "borrower")
    private List<Transaction> transactions;

    @JsonManagedReference
    @OneToMany(mappedBy = "borrower")
    private List<Reservation> reservations;
}
