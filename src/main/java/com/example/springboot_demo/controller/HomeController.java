package com.example.springboot_demo.controller;

import com.example.springboot_demo.service.SecurityService;
import com.example.springboot_demo.service.UserServiceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/")
public class HomeController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private SecurityService securityService;

    @GetMapping("")
    public Map<String, String> home() {
        Map<String, String> res = this.userService.getMessage();
        return res;
    }
    @GetMapping("security/generate/token") // 토큰 생성
    public Map<String, Object> generateToken(@RequestParam String subject) {
        String token = securityService.createToken(subject, 1000 * 60 * 60 * 24L);
        Map<String, Object> map = new HashMap<>();
        map.put("userid", subject);
        map.put("token", token);
        return map;
    }
    
    @GetMapping("security/get/subject") // 클라이언트가 요청할때 받은 토큰에서 서브젝트를 추출
    public String getSubject(@RequestParam String token) {
        String subject = securityService.getSubject(token);
        return subject;
    }
}
