package com.example.gestionbibliotheque.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Author")
@NoArgsConstructor // Lombok-generated no-args constructor
@AllArgsConstructor // Lombok-generated all-args constructor
@Data
@Builder
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;


    @Override
    public String toString() {
        return "Author [id=" + id + ", name=" + name + "]";
    }
}
