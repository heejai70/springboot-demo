package com.example.springboot_demo.service;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.stereotype.Service;
//import org.yaml.snakeyaml.error.YAMLException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class SecurityServiceImpl implements SecurityService {

    // 서버가 가지고 있는 비밀키
    public static final String secretKey = "4C8kum4LxyKWYLM78sKdXrzbBjDCFyfX";

    @Override
    // subject : Client식별자 - userid
    public String createToken(String subject, long ttlMillis) {
        if(ttlMillis == 0) {
            throw new RuntimeException("토큰 만료기간은 0 이상이어야 합니다.");
        }
        //JCA알고리즘(HS256) 방식으로 secretKey를 암호화(signingKey)
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] apiKeySecreBytes = DatatypeConverter.parseBase64Binary(secretKey);

        Key signingKey = new SecretKeySpec(apiKeySecreBytes, signatureAlgorithm.getJcaName());

        //JwtBuilder : 토큰 빌더 Object
        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .signWith(signatureAlgorithm, signingKey);

        long nowMillis = System.currentTimeMillis();
        //토큰의 만료 기간 설정
        builder.setExpiration(new Date(nowMillis + ttlMillis));
        //compact() : 토큰을 생성하고 컴팩트하게 Serialize
        return builder.compact();
    }
    
    @Override
    public String getSubject(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public String get(String token, String key) {
        String value = Jwts.parser()
                .setSigningKey((DatatypeConverter.parseBase64Binary(secretKey)))
                .parseClaimsJwt(token)
                .getBody()
                .get(key, String.class);
        return value;
    }
}
