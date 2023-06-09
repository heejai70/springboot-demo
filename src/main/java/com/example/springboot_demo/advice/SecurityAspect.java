package com.example.springboot_demo.advice;

import com.example.springboot_demo.annotation.TokenRequired;
import com.example.springboot_demo.service.SecurityServiceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

@Aspect
@Component
public class SecurityAspect {
    @Before("@annotation(tokenRequired)")
    public void authWithToken(TokenRequired tokenRequired) {
        ServletRequestAttributes reqAttributes = 
                (ServletRequestAttributes)
                    RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = reqAttributes.getRequest();
        //check for token in request header
        String tokenInHeader = request.getHeader("token");
        if(!StringUtils.hasText(tokenInHeader)) {
            throw new IllegalArgumentException("Empty token");
        }
        Claims claims = Jwts.parser().setSigningKey(
                DatatypeConverter.parseBase64Binary(
                    SecurityServiceImpl.secretKey))
                    .parseClaimsJws(tokenInHeader).getBody();
        if(claims == null || claims.getSubject() == null) {
            throw new IllegalArgumentException("Token Error : Claim is null");
        }
        System.out.println("토큰에 포함된 subject로 자체 인증처리 필요");
    }    
}
