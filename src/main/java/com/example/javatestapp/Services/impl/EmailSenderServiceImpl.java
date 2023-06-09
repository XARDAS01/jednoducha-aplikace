package com.example.javatestapp.Services.impl;

import com.example.javatestapp.Models.AbstractEmailContext;
import com.example.javatestapp.Services.EmailSenderService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.thymeleaf.context.Context;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;

@Service
public class EmailSenderServiceImpl implements EmailSenderService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Override
    public void sendSimpleEmail (String toEmail, String subjectEmail, String bodyEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("yuri.raduntcev@gmail.com");
        message.setTo(toEmail);
        message.setSubject(subjectEmail);
        message.setText(bodyEmail);

        javaMailSender.send(message);
    }

    @Override
    public void sendEmailWithAttachment(String toAddress, String subject, String message, File file) throws MessagingException, FileNotFoundException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
        messageHelper.setTo(toAddress);
        messageHelper.setSubject(subject);
        messageHelper.setText(message);
        messageHelper.addAttachment("Purchase Order", file);

        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendEmailWithHtmlTemplate(AbstractEmailContext email) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());
        Context context = new Context();
        context.setVariables(email.getContext());
        String emailContent = templateEngine.process(email.getTemplateLocation(), context);

        mimeMessageHelper.setTo(email.getTo());
        mimeMessageHelper.setSubject(email.getSubject());
        mimeMessageHelper.setFrom(email.getFrom());
        mimeMessageHelper.setText(emailContent, true);

        javaMailSender.send(message);
    }
}
