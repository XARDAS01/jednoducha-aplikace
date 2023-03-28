package com.example.javatestapp.Models;

import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name, isbn, authorUid;
    private Date lastEditDate;

    public Book() { }

    public Book(String name, String isbn, String authorUid) {
        this.name = name;
        this.isbn = isbn;
        this.authorUid = authorUid;
        this.lastEditDate = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.lastEditDate = new Date();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
        this.lastEditDate = new Date();
    }

    public Date getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(Date lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

    public String getAuthorUid() {
        return authorUid;
    }

    public void setAuthorUid(String authorUid) {
        this.authorUid = authorUid;
        this.lastEditDate = new Date();
    }
}
