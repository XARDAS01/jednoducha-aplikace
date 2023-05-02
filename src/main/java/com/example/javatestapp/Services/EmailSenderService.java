package com.example.javatestapp.Services;

import com.example.javatestapp.Models.AbstractEmailContext;
import com.example.javatestapp.Models.Email;
import jakarta.mail.MessagingException;

import java.io.File;
import java.io.FileNotFoundException;

public interface EmailSenderService {
    void sendSimpleEmail (String toEmail, String subjectEmail, String bodyEmail);

    void sendEmailWithAttachment (String toAddress, String subject, String message, File file) throws MessagingException, FileNotFoundException;

    void sendEmailWithHtmlTemplate (AbstractEmailContext abstractEmailContext) throws MessagingException;
}
