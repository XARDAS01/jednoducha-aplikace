package com.example.javatestapp.Models;

import com.example.javatestapp.Services.GeneratorService;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "tokens")
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String token = GeneratorService.generateToken();
    @NotNull
    private String uid;

    public Token() { }

    public Token(@NotNull String uid) {
        this.uid = uid;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getUid() {
        return uid;
    }
}
