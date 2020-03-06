package com.example.demo.test.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.UUID;

public class JwtUtil {

    public static void main(String[] args){

        System.out.println(createJWT(600000));

    }

    //参数为token过期时间，单位为毫秒
    public static String createJWT(long ttlMillis) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        String secret = "c39zeyoSAg3ubsdQOGj2QuzO4KdB4uVc"; //此处字符串对应consumer中jwt参数的secret

        String uuid = UUID.randomUUID().toString();
        System.out.println(uuid);
        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setIssuer("woJcpcvdiSpIvsMW6VNF2EOGNPcb47Fz") //此处字符串对应consumer中jwt参数的key
                .setIssuedAt(new Date())
                .setSubject(uuid)
                .signWith(signatureAlgorithm, secret);

        //if it has been specified, let's add the expiration
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

//        long nbf = nowMillis + 600000;
//        Date nbfDate = new Date(nbf);
//        builder.setNotBefore(nbfDate);

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();

    }

}
