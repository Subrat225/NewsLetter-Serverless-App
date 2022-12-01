package com.example.serverless.service.impl;

import com.example.serverless.service.MailerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class JavaMailerServiceImpl implements MailerService {

    private final JavaMailSender javaMailSender;

    private final String senderMail;

    public JavaMailerServiceImpl(JavaMailSender javaMailSender,
                                 @Value("${spring.mail.username}") String senderMail) {
        this.javaMailSender = javaMailSender;
        this.senderMail = senderMail;
    }

    @Override
    public void sendTextMessage(String to, String subject, String text) {
        final SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(senderMail);
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        javaMailSender.send(mailMessage);
    }
}
