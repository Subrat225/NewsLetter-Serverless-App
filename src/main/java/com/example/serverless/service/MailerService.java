package com.example.serverless.service;

public interface MailerService {
    void sendTextMessage(String to, String subject, String text);
}
