package com.example.javatestapp.Controllers;

import com.example.javatestapp.Models.Book;
import com.example.javatestapp.Models.Token;
import com.example.javatestapp.Payloads.Requests.BookAddRequest;
import com.example.javatestapp.Payloads.Responses.MessageResponse;
import com.example.javatestapp.Repositories.BookRepository;
import com.example.javatestapp.Repositories.TokenRepository;
import com.example.javatestapp.Services.CsvExportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = {"api/v1/book"})
public class BookController {
    @Autowired(required = false)
    private BookRepository bookRepository;

    @Autowired(required = false)
    private TokenRepository tokenRepository;

    @PostMapping("/add")
    private MessageResponse add (@RequestBody BookAddRequest bookAddRequest) {
        try {
            if (tokenRepository.findByToken(bookAddRequest.getToken()) == null) { return new MessageResponse("token not found", 404); }
            if (bookRepository.findByIsbn(bookAddRequest.getIsbn()) != null) { return new MessageResponse("book ISBN already exist", 400); }
            if (bookAddRequest.getIsbn() == null) { return new MessageResponse("book ISBN is null", 400); }

            Book book = new Book(
                    bookAddRequest.getName(),
                    bookAddRequest.getIsbn(),
                    tokenRepository.findByToken(bookAddRequest.getToken()).getUid()
            );
            bookRepository.save(book);
            return new MessageResponse("ok", 201, book);
        } catch (Exception e) { return new MessageResponse(e.toString(), 500); }
    }

    @PostMapping("/edit")
    private MessageResponse edit (@RequestParam int bookId, @RequestBody BookAddRequest bookAddRequest) {
        try {
            if (tokenRepository.findByToken(bookAddRequest.getToken()) == null) { return new MessageResponse("token not found", 400); }
            if (bookRepository.findByIsbn(bookAddRequest.getIsbn()) != null) { return new MessageResponse("book ISBN already exist", 400); }
            if (bookAddRequest.getIsbn() == null) { return new MessageResponse("book ISBN is null", 400); }

            Book book = bookRepository.findById(bookId);
            book.setName(bookAddRequest.getName());
            book.setIsbn(bookAddRequest.getIsbn());
            bookRepository.save(book);
            return new MessageResponse("ok", 201, book);
        } catch (Exception e) { return new MessageResponse(e.toString(), 500); }
    }

    @GetMapping("/getByName")
    private MessageResponse getAll (@RequestParam String name, @RequestParam String token) {
        try {
            if (tokenRepository.findByToken(token) == null) { return new MessageResponse("token not found", 400); }
            if (bookRepository.findByName(name) == null) { return new MessageResponse("Book not found", 400); }
            if (tokenRepository.findByToken(token).getUid().equals(bookRepository.findByName(name).getAuthorUid())) { return new MessageResponse("User not the same that book author", 400); }

            return new MessageResponse("ok", 200, bookRepository.findByName(name));
        } catch (Exception e) { return new MessageResponse(e.toString(), 500); }
    }

    @GetMapping("/getById")
    private MessageResponse getById (@RequestParam long id) {
        try {
            if (bookRepository.findById(id) == null) { return new MessageResponse("book not found", 404); }
            return new MessageResponse("ok", 200, bookRepository.findById(id));
        } catch (Exception e) { return new MessageResponse(e.toString(), 500); }
    }

    @GetMapping("/getAllByUid")
    private MessageResponse getAllByUid (@RequestParam String token) {
        try {
            if (tokenRepository.findByToken(token) == null) { return new MessageResponse("token not found", 400); }

            Token userToken = tokenRepository.findByToken(token);
            ArrayList<Book> books = bookRepository.findAllByAuthorUid(userToken.getUid());
            return new MessageResponse("ok", 200, books);
        } catch (Exception e) { return new MessageResponse(e.toString(), 500); }
    }

    @GetMapping("/getAll")
    private MessageResponse getAll () {
        try {
            ArrayList<Book> books = bookRepository.findAll();
            return new MessageResponse("ok", 200, books);
        } catch (Exception e) { return new MessageResponse(e.toString(), 500); }
    }

    @DeleteMapping("/delete")
    private MessageResponse delete (@RequestParam int bookId, @RequestParam String token) {
        try {
            if (tokenRepository.findByToken(token) == null) { return new MessageResponse("token not found", 400); }
            if (bookRepository.findById(bookId) == null) { return new MessageResponse("book not found", 404); }
            if (tokenRepository.findByToken(token).getUid().equals(bookRepository.findById(bookId).getAuthorUid())) { return new MessageResponse("User not the same that book author", 400); }

            bookRepository.delete(bookRepository.findById(bookId));
            return new MessageResponse("ok", 200);
        } catch (Exception e) { return new MessageResponse(e.toString(), 500); }
    }

    @RequestMapping(path = "/export")
    public void getAllEmployeesInCsv(HttpServletResponse servletResponse) throws IOException {
        List<Book> books = bookRepository.findAll();
        CsvExportService.exportBooks(servletResponse, books, "books");
    }
}
