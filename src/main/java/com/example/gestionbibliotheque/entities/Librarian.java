package com.example.gestionbibliotheque.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Librarian")
@DiscriminatorValue(value = "Librarian")
@Data

public class Librarian extends User {




}
