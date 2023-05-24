package com.cos.jwt.Filter;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyFilter1 implements Filter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String headerAuth = req.getHeader("Authorization");
        System.out.println(headerAuth);
        System.out.println("필터1");

        chain.doFilter(req, res); //다시 필터 체인 타라고 등록(계속 프로세스를 진행해)


    }
}
