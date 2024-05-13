package com.example.gestionbibliotheque.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "message")
    private String message;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt")
    private Date createAt;

    @ManyToOne
    private User recipient;

    @Override
    public String toString() {
        return "Notification [id=" + id + ", message=" + message + ", createdAt=" + createAt + ", recipient="
                + recipient + "]";
    }





}