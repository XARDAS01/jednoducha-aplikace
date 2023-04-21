package com.example.javatestapp.Controllers;

import com.example.javatestapp.Services.EmailSenderService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = {"api/v1/email"})
public class EmailSenderController {
    @Autowired
    private EmailSenderService emailService;

    @GetMapping("/send-simple-email")
    public void simpleEmail () {
        emailService.sendSimpleEmail ("yuri.raduntcev@gmail.com", "test message subject", "hello email sending world!");
    }

    @GetMapping("/send-email-with-attachment")
    public void emailWithAttachment () throws MessagingException, FileNotFoundException {
        emailService.sendEmailWithAttachment ("yuri.raduntcev@gmail.com", "test message subject", "hello email sending world!", "attachment");
    }
}