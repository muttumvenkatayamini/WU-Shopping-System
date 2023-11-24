package com.cartServicet.demo.Service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cartServicet.demo.CartDto.UserDetails;

@Service
public class SessionValidationService {
	private Map<String, UserDetails> sessionMap = new HashMap<>();
	
//	@Autowired
//	private SessionRepo sessionRep;
	
	public boolean isValidaSession(String sessionId) {
		return sessionMap.containsKey(sessionId);
		
	}
	
	public void addValidSession(String sessionId, UserDetails userDetails) {
		sessionMap.put(sessionId, userDetails);
		
	}
	public void removeSessionId(String sessionId) {
		sessionMap.remove(sessionId);
	}
	
	
	

}
