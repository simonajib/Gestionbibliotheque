package com.example.gestionbibliotheque.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Entity
@Table(name = "Book")
@JsonIgnoreProperties("inspection")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Book {
    @Id
    @Column(name = "isbn")
    private Long isbn;

    @Column(name = "title")
    private String title;

    private String description;
    private String imagePath;


    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "edition")
    private String edition;

    private String publisher;

    @Column(name = "number_of_pages")
    private int numberOfPages;

    @Column(name = "language")
    private String language;

    @Column(columnDefinition="tinyint(1) default 0")
    private boolean available;

    @ManyToOne
    private User reservedByUser;
    private boolean readyForPickUp;
    @JsonInclude
    @OneToMany(mappedBy = "book")
    private List<BookItem> bookItems;

    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<Transaction> transactions;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;



    public User getReservedByUser() {
        return reservedByUser;
    }

    public void setReservedByUser(User reservedByUser) {
        this.reservedByUser = reservedByUser;
    }

    public boolean getReadyForPickUp() {
        return readyForPickUp;
    }

    public void setReadyForPickUp(boolean readyForPickUp) {
        this.readyForPickUp = readyForPickUp;
    }

    @Override
    public String toString() {
        return "Book [isbn=" + isbn + ", title=" + title + ", description=" + description + ", imagePath=" + imagePath
                + ", author=" + (author != null ? author.getName() : "Unknown") + ", edition=" + edition + ", publisher=" + publisher + ", numberOfPages="
                + numberOfPages + ", language=" + language + ", availability=" + available + ", category=" + category
                + "]";
    }









}
