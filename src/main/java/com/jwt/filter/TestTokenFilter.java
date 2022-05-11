package com.jwt.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
public class TestTokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (httpRequest.getMethod().equals("POST")) {
            String headerAuth = httpRequest.getHeader("Authorization");
            log.info("[HeaderAuth] : {}", headerAuth);

            if(headerAuth.equals("valid")) {
                chain.doFilter(httpRequest, httpResponse);
                return;
            }
            PrintWriter writer = httpResponse.getWriter();
            writer.println("인증되지 않은 요청");
        }
    }
}
