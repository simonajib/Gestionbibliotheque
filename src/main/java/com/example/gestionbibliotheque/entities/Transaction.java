package com.example.gestionbibliotheque.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity(name = "Transaction")
@Table(name = "transaction")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_issue")
    private Date dateOfIssue;

    @Temporal(TemporalType.DATE)
    @Column(name = "return_date")
    private Date returnDate;
    @ManyToOne
    private BookItem bookItem;

    @ManyToOne
    private Book book;

    @JsonBackReference
    @ManyToOne
    private User borrower;

    private boolean returned;


    @Override
    public String toString() {
        return "Transaction [id=" + id + ", dateOfIssue=" + dateOfIssue + ", returnDate=" + returnDate + ", bookItem="
                + bookItem + ", borrower=" + borrower + "]";
    }




}
