package com.example.filter;

import com.example.utils.CurrentHolder;
import com.example.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        String request1URI = request.getRequestURI();

        //判斷是否為登錄請求
        if (request1URI.contains("/login")){
            log.info("登錄請求,放行");
            filterChain.doFilter(request, response);
            return;
        }
        //獲取Token
        String token = request.getHeader("token");

        //判斷Token是否存在
        if (token == null || token.isEmpty()){
            log.info("令牌為空,響應401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //校驗token
        try {
            Claims claims = JwtUtils.parseToken(token);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
            log.info("當前登錄員工的ID:{},存入ThreadLocal",empId);
        } catch (Exception e) {
            log.info("令牌不存在,響應401");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        //校驗通過
        log.info("令牌存在,通過");
        filterChain.doFilter(request, response);

        //刪除ThreadLocal中的資料
        CurrentHolder.remove();
    }
}
