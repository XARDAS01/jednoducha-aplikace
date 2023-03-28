package com.example.javatestapp.Controllers;

import com.example.javatestapp.Models.Token;
import com.example.javatestapp.Payloads.Responses.MessageResponse;
import com.example.javatestapp.Repositories.TokenRepository;
import com.example.javatestapp.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = {"api/v1/token"})
public class TokenController {
    @Autowired(required = false)
    private TokenRepository tokenRepository;

    @Autowired(required = false)
    private UserRepository userRepository;

    @DeleteMapping("/delete")
    private MessageResponse delete (@RequestParam String token) {
        try {
            if (tokenRepository.findByToken(token) == null) { return new MessageResponse("token not found", 404); }
            Token userToken = tokenRepository.findByToken(token);
            tokenRepository.delete(userToken);
            return new MessageResponse("ok", 200);
        }
        catch (Exception e) { return new MessageResponse(e.toString(), 500); }
    }

    @GetMapping("getAll")
    private MessageResponse getAll () {
        try {
            ArrayList<Token> tokens = tokenRepository.findAll();
            return new MessageResponse("ok", 200, tokenRepository.findAll());
        } catch (Exception e) { return new MessageResponse(e.toString(), 500); }
    }
}
