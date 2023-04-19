package com.example.javatestapp.Services;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail (String toEmail, String subjectEmail, String bodyEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("yuri.raduntcev@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subjectEmail);
        message.setText(bodyEmail);

        javaMailSender.send(message);
    }

    public void sendEmailWithAttachment (String toEmail, String subjectEmail, String bodyEmail) throws MailException, MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(toEmail);
        helper.setSubject(subjectEmail);
        helper.setText(bodyEmail);

        ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
        helper.addAttachment(classPathResource.getFilename(), classPathResource);

        javaMailSender.send(mimeMessage);
    }
}
