package com.cos.jwt.Filter;

import javax.servlet.*;
import java.io.IOException;

public class MyFilter2 implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("필터2");
        chain.doFilter(request, response); //다시 필터 체인 타라고 등록(계속 프로세스를 진행해)
    }
}
