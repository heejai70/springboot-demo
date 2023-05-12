package com.example.springboot_demo.service;

public interface SecurityService {
    String createToken(String subject, long ttlMillis);
    String getSubject(String token);
}
