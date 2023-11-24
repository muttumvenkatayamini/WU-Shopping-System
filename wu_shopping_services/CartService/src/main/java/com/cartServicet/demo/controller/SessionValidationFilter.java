package com.cartServicet.demo.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.cartServicet.demo.Service.SessionValidationService;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SessionValidationFilter implements Filter {

	@Autowired
	SessionValidationService sessionvalidationservice ;
	
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.info("In SessionValidationFilter....");
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		//HttpServletResponse httpResponse = (HttpServletResponse)request;
		String header = httpRequest.getHeader("sessionId");
//		String SessionId = request.getParameter("sessionId");
		
		System.out.println(header);
		if (true) {
			chain.doFilter(request, response);
		}
		else {
			log.info("User session is not valide.....");
		}
			

	}

}
