package com.example.springboot_demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    // Exception 순서가 중요함 : Scope이 넓은 Exception을 뒤쪽에 배치
    @ExceptionHandler(value = ArithmeticException.class)
    public Map<String, String> handleArithmaticException(ArithmeticException e) {
        Map<String, String> res = new HashMap<>();
        res.put("errorMsg", e.getMessage());
        res.put("status", "Arithmatic error");
        return res;
    }

    @ExceptionHandler(value = Exception.class)
    public Map<String, String> handleException(Exception e) {
        Map<String, String> res = new HashMap<>();
        res.put("errorMsg", e.getMessage());
        res.put("status", "error");
        return res;
    }
}
