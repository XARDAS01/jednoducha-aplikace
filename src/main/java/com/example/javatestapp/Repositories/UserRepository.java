package com.example.javatestapp.Repositories;

import com.example.javatestapp.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findById(long id);

    User findByUid(String uid);

    User findByLogin(String login);


    ArrayList<User> findAll();
}
