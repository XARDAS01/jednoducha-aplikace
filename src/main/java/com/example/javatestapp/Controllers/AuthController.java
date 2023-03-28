package com.example.javatestapp.Controllers;

import com.example.javatestapp.Models.Token;
import com.example.javatestapp.Models.User;
import com.example.javatestapp.Payloads.Requests.AuthLoginRequest;
import com.example.javatestapp.Payloads.Requests.AuthRegistrationRequest;
import com.example.javatestapp.Payloads.Responses.MessageResponse;
import com.example.javatestapp.Repositories.TokenRepository;
import com.example.javatestapp.Repositories.UserRepository;
import com.example.javatestapp.Services.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = {"api/v1/auth"})
public class AuthController {
    @Autowired(required = false)
    private UserRepository userRepository;

    @Autowired(required = false)
    private TokenRepository tokenRepository;

    @PostMapping("/registration")
    private MessageResponse registration (@RequestBody AuthRegistrationRequest authRegistrationRequest) {
        try {
            if (userRepository.findByLogin(authRegistrationRequest.getLogin()) != null) { return new MessageResponse("User with this login already exist", 400); }
            if (authRegistrationRequest.getLogin() == null) { return new MessageResponse("User login is null", 400); }

            User user = new User(authRegistrationRequest);
            userRepository.save(user);

            Token token = new Token(user.getUid());
            tokenRepository.save(token);

            return new MessageResponse("User registered", 201, token);
        }
        catch (Exception e) { return new MessageResponse(e.toString(), 500); }
    }

    @PostMapping("/login")
    private MessageResponse login (@RequestBody AuthLoginRequest authLoginRequest) {
        try {
            if (userRepository.findByLogin(authLoginRequest.getLogin()) == null) { return new MessageResponse("user not found", 404); }

            User user = userRepository.findByLogin(authLoginRequest.getLogin());
            if (user.getPassword().equals(GeneratorService.generateHash(authLoginRequest.getPassword()))) {
                tokenRepository.delete(tokenRepository.findByUid(user.getUid()));
                Token token = new Token(user.getUid());
                tokenRepository.save(token);

                return new MessageResponse("ok", 200, token);
            } else {  return new MessageResponse("password is not correct", 400); }
        } catch (Exception e) { return new MessageResponse(e.toString(), 500); }
    }
}
