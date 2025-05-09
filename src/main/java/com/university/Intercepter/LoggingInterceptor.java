package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

public class LoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        HttpSession session = request.getSession(false);

        if (uri.contains("/login")) {
            logger.info("Login attempt at: " + LocalDateTime.now());
        }

        if (uri.contains("/register")) {
            Integer studentId = (session != null) ? (Integer) session.getAttribute("studentId") : null;
            if (studentId != null) {
                logger.info("Student ID " + studentId + " is attempting to register for a course at: " + LocalDateTime.now());
            } else {
                logger.warn("Unauthorized course registration attempt detected at: " + LocalDateTime.now());
            }
        }

        return true;
    }

    
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String uri = request.getRequestURI();
        if (uri.contains("/login") && modelAndView != null) {
            if (modelAndView.getViewName().equals("redirect:/courses")) {
                logger.info("Login successful at: " + LocalDateTime.now());
            } else {
                logger.warn("Login failed at: " + LocalDateTime.now());
            }
        }
    }

  
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        
    }
}
