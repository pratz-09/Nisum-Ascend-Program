package com.example.assignment1.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailSender {
    void send(String to, String subject, String body);
}