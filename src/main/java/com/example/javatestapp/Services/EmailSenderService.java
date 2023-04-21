package com.example.javatestapp.Services;

import jakarta.mail.MessagingException;

import java.io.FileNotFoundException;

public interface EmailSenderService {
    void sendSimpleEmail (String toEmail, String subjectEmail, String bodyEmail);

    void sendEmailWithAttachment (String toAddress, String subject, String message, String attachment) throws MessagingException, FileNotFoundException;
}
