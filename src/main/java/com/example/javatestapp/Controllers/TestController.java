package com.example.javatestapp.Controllers;

import com.example.javatestapp.Payloads.Responses.MessageResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = {"api/v1"})
public class TestController {
    @GetMapping
    public MessageResponse testController () {
        return new MessageResponse("Hello world!", 200);
    }
}
