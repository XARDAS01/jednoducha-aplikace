package com.example.javatestapp.Controllers;

import com.example.javatestapp.Payloads.Responses.MessageResponse;
import com.example.javatestapp.Services.EmailSenderService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = {"api/v1"})
public class TestController {
    private EmailSenderService emailSenderService;
    @GetMapping("/email-send")
    public MessageResponse emailSend () {
        try {
            emailSenderService.sendEmail("raduntseff@yandex.ru", "test message subject", "hello email sending world!");
            return new MessageResponse("Hello world!", 200);
        } catch (Exception e) { return new MessageResponse("error", 500, e); }
    }

    @GetMapping("/email-send-attachment")
    public MessageResponse emailSendAttachment () {
        try {
            emailSenderService.sendEmailWithAttachment("raduntseff@yandex.ru", "test message subject", "hello email sending world!");
            return new MessageResponse("Hello world!", 200);
        } catch (Exception e) { return new MessageResponse("error", 500, e); }
    }
}
