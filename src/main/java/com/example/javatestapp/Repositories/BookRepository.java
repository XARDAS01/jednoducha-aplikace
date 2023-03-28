package com.example.javatestapp.Repositories;

import com.example.javatestapp.Models.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    Book findById(long id);

    Book findByAuthorUid(String authorUid);

    Book findByIsbn(String isbn);

    Book findByName(String name);

    ArrayList<Book> findAllByAuthorUid(String authorUid);

    ArrayList<Book> findAll();
}
