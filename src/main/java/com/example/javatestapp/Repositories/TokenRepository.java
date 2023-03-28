package com.example.javatestapp.Repositories;

import com.example.javatestapp.Models.Token;
import com.example.javatestapp.Models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface TokenRepository extends CrudRepository<Token, Long> {
    Token findByUid(String uid);

    Token findByToken(String token);

    ArrayList<Token> findAll();
}
