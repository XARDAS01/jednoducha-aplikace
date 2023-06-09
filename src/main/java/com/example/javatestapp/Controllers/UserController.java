package com.example.javatestapp.Controllers;

import com.example.javatestapp.Models.Book;
import com.example.javatestapp.Models.User;
import com.example.javatestapp.Payloads.Requests.BookAddRequest;
import com.example.javatestapp.Payloads.Requests.SendSimpleEmailRequest;
import com.example.javatestapp.Payloads.Requests.UserEditRequest;
import com.example.javatestapp.Payloads.Responses.MessageResponse;
import com.example.javatestapp.Repositories.TokenRepository;
import com.example.javatestapp.Repositories.UserRepository;
import com.example.javatestapp.Services.CsvExportService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = {"api/v1/user"})
public class UserController {
    @Autowired(required = false)
    private UserRepository userRepository;

    @Autowired(required = false)
    private TokenRepository tokenRepository;

    @DeleteMapping("/delete")
    private MessageResponse registration (@RequestParam String uid) {
        try {
            if (userRepository.findByUid(uid) == null) { return new MessageResponse("user not found", 404); }
            User user = userRepository.findByUid(uid);
            userRepository.delete(user);
            return new MessageResponse("ok", 200);
        }
        catch (Exception e) { return new MessageResponse(e.toString(), 500); }
    }

    @GetMapping("/getByUid")
    private MessageResponse getByUid (@RequestParam String uid) {
        try {
            if (userRepository.findByUid(uid) == null) { return new MessageResponse("user not found", 404); }
            User user = userRepository.findByUid(uid);
            return new MessageResponse("ok", 200, user);
        }
        catch (Exception e) { return new MessageResponse(e.toString(), 500); }
    }

    @GetMapping("/getById")
    private MessageResponse getById (@RequestParam long id) {
        try {
            if (userRepository.findById(id) == null) { return new MessageResponse("user not found", 404); }
            User user = userRepository.findById(id);
            return new MessageResponse("ok", 200, user);
        }
        catch (Exception e) { return new MessageResponse(e.toString(), 500); }
    }

    @GetMapping("/getByToken")
    private MessageResponse getByToken (@RequestParam String token) {
        try {
            if (tokenRepository.findByToken(token) == null) { return new MessageResponse("token not found", 404); }
            User user = userRepository.findByUid(tokenRepository.findByToken(token).getUid());
            return new MessageResponse("ok", 200, user);
        }
        catch (Exception e) { return new MessageResponse(e.toString(), 500); }
    }

    @GetMapping("/getAll")
    private MessageResponse getAll () {
        try {
            ArrayList<User> users = userRepository.findAll();
            return new MessageResponse("ok", 200, users);
        }
        catch (Exception e) { return new MessageResponse(e.toString(), 500); }
    }

    @PostMapping("/edit")
    private MessageResponse edit (@RequestParam int userId, @RequestBody UserEditRequest userEditRequest) {
        try {
            User user = userRepository.findById(userId);
            user.setName(userEditRequest.getName());
            user.setSurname(userEditRequest.getSurname());
            user.setEmail(userEditRequest.getEmail());
            user.setLogin(userEditRequest.getLogin());
            user.setPassword(userEditRequest.getPassword());
            user.setUid(userEditRequest.getUid());
            userRepository.save(user);
            return new MessageResponse("ok", 201, user);
        } catch (Exception e) { return new MessageResponse(e.toString(), 500); }
    }

    @RequestMapping(path = "/export")
    public void getAllUsersInCsv(HttpServletResponse servletResponse) throws IOException {
        List<User> users = userRepository.findAll();
        CsvExportService.exportUsers(servletResponse, users, "users");
    }
}
