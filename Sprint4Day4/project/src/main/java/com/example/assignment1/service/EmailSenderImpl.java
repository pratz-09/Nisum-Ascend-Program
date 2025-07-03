package com.example.assignment1.service;

import org.springframework.stereotype.Service;

@Service
public class EmailSenderImpl implements EmailSender {
    
    @Override
    public void send(String to, String subject, String body) {
        // In a real application, this would send an actual email
        System.out.println("Sending email to: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
    }
}