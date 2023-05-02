package com.example.javatestapp.Controllers;

import com.example.javatestapp.Models.AbstractEmailContext;
import com.example.javatestapp.Models.Email;
import com.example.javatestapp.Models.User;
import com.example.javatestapp.Payloads.Requests.SendSimpleEmailRequest;
import com.example.javatestapp.Payloads.Responses.MessageResponse;
import com.example.javatestapp.Repositories.UserRepository;
import com.example.javatestapp.Services.CsvExportService;
import com.example.javatestapp.Services.EmailSenderService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(path = {"api/v1/email"})
public class EmailSenderController {
    @Autowired
    private EmailSenderService emailService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/send-simple-email")
    public MessageResponse simpleEmail (@RequestBody SendSimpleEmailRequest sendSimpleEmailRequest) {
        try {
            emailService.sendSimpleEmail (sendSimpleEmailRequest.getTo(), sendSimpleEmailRequest.getSubject(), sendSimpleEmailRequest.getBody());
            return new MessageResponse("ok", 200);
        } catch (Exception e) {
            return new MessageResponse("some error", 400, e);
        }
    }

    @PostMapping("/send-email-with-attachment")
    public MessageResponse emailWithAttachment (@RequestBody SendSimpleEmailRequest sendSimpleEmailRequest) throws MessagingException, FileNotFoundException {
        try {
            List<User> users = userRepository.findAll();
            CsvExportService.smartExportUsers(users, "users");

            File file = new File("./src/main/assets/users.csv");
            emailService.sendEmailWithAttachment (sendSimpleEmailRequest.getTo(), sendSimpleEmailRequest.getSubject(), sendSimpleEmailRequest.getBody(), file);
            return new MessageResponse("ok", 200);
        } catch (Exception e) {
            return new MessageResponse("some error", 400, e);
        }
    }

    @GetMapping("/send-email-with-html-template")
    public MessageResponse emailWithHtmlTemplate (@RequestParam String appeal, @RequestParam String prize) {
        try {
            Map<String, Object> map = new HashMap<>(){};
            map.put("appeal", appeal);
            map.put("prize", prize);
            AbstractEmailContext abstractEmailContext = new AbstractEmailContext("yuri.raduntcev@gmail.com", "testHtml", "", "", "", "", "", "welcome-email.html", map);
            emailService.sendEmailWithHtmlTemplate(abstractEmailContext);
            return new MessageResponse("ok", 200);
        } catch (Exception e) {
            return new MessageResponse("some error", 400, e);
        }
    }
}