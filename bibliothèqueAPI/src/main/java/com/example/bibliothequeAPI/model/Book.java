package com.example.bibliothequeAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Il manque le titre du livre")
    private String title;

    @NotBlank(message = "Il manque le nom de l'auteur")
    private String author;

    private boolean available;

    public Book() {}

    public Book(String title, String author, boolean available) {
        this.title = title;
        this.author = author;
        this.available = available;

    }

    public Long getid() {
        return (long) id;
    }
    public void setid(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }

}
