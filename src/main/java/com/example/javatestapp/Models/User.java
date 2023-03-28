package com.example.javatestapp.Models;

import com.example.javatestapp.Payloads.Requests.AuthRegistrationRequest;
import com.example.javatestapp.Services.GeneratorService;
import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String name, surname, email, login, password;
    private String uid = GeneratorService.generateUID();

    public User() { }

    public User(AuthRegistrationRequest request) {
        this.name = request.getName();
        this.surname = request.getSurname();
        this.email = request.getEmail();
        this.login = request.getLogin();
        this.password = GeneratorService.generateHash(request.getPassword());
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Long getId() {
        return id;
    }
}
