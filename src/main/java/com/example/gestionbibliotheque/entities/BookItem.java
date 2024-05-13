package com.example.gestionbibliotheque.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookItem {

    @Id
    @Column(name = "barcode")
    private Long barcode;


    @ManyToOne
    private Book book;



    @Override
    public String toString() {
        return "BookItem [barcode=" + barcode + ", book=" + book + "]";
    }





}
