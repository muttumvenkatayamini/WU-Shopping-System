package com.wu.apigateway.util;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wu.apigateway.entity.LoginSession;
import com.wu.apigateway.repository.LoginSessionRepository;

@Service
public class SessionUtil {

	@Autowired
	private LoginSessionRepository loginSessionRepository;

	public void validateSession(String username, String sessionId) {
		Optional<LoginSession> list = loginSessionRepository.findAllByUserNameAndSessionIdWithCreationDateTimeAfter(username, sessionId, new Date());
		if (list.isEmpty()) {
			throw new RuntimeException("Session Expired - Please login and provide valid Session ID");
		}

	}

}
