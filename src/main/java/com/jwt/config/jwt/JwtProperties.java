package com.jwt.config.jwt;

public class JwtProperties {
    static String SECRET = "shine_secret_key"; // 우리 서버만 알고 있는 비밀값
    static int EXPIRATION_TIME = 600000; // 10일 (1/1000초)
    static String TOKEN_PREFIX = "Bearer ";
    static String HEADER_STRING = "Authorization";

    private JwtProperties() {
    }
}
