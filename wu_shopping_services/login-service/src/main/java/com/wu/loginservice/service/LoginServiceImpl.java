package com.wu.loginservice.service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wu.loginservice.dto.LoginDto;
import com.wu.loginservice.dto.LoginResponse;
import com.wu.loginservice.dto.SessionResponse;
import com.wu.loginservice.entity.LoginSession;
import com.wu.loginservice.entity.Users;
import com.wu.loginservice.repository.LoginSessionRepository;
import com.wu.loginservice.repository.UsersRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private LoginSessionRepository loginSessionrepository;

//	@Override
//	public String loginUser(LoginDto loginDto) {
//		log.info("Inside  loginUser of LoginServiceImpl");
//		Users user = modelMapper.map(loginDto, Users.class);
//		Optional<Users> users = usersRepository.findByUserNameAndPassword(user.getUserName(),
//				user.getPassword());
//		if (users.isPresent()) {
//			log.info("successfully loged");
//			getSessionData(loginDto);
//			return "successfully loged";
//		} else {
//			log.error("successfully loged");
//			return "Invalid Username or Password";
//		}
//	}

	@Override
	public LoginResponse loginUser(LoginDto loginDto) {
		// TODO Auto-generated method stub
		log.info("Inside  loginUser of LoginServiceImpl");
		Users user = modelMapper.map(loginDto, Users.class);
//		System.out.println(new BCryptPasswordEncoder().encode(user.getPassword()));
//		System.out.println("@@@@@@"+passwordEncoder.encode(user.getPassword()));
//		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		String findPassword = usersRepository.findPassword(user.getUsername());
		System.out.println(findPassword);
		System.out.println(bCryptPasswordEncoder.matches(user.getPassword(), findPassword));
//		Optional<Users> users = usersRepository.findByUsernameAndPassword(user.getUsername(),
//				user.getPassword());
//		if (users.isPresent()) {
		LoginResponse loginResponse = new LoginResponse();
		if (bCryptPasswordEncoder.matches(user.getPassword(), findPassword)) {
			log.info("User successfully logged-in");
			String token = restTemplate.postForObject("http://localhost:8080/auth/token", user, String.class);
			String sessionId = getSessionData(user.getUsername());
			loginResponse.setUsername(user.getUsername());
			loginResponse.setSessionId(sessionId);
			loginResponse.setToken(token);
			loginResponse.setMessage("User Successfully logged-in");
			return loginResponse;
		} else {
			log.error("Invalid Username or Password");
			loginResponse.setMessage("Invalid Username or Password");
			return loginResponse;
		}
	}

	@Override
	public String getSessionData(String username) {
		Optional<LoginSession> list = loginSessionrepository.findAllByUserNameWithCreationDateTimeAfter(username,
				new Date());
		if (list.isPresent()) {
			log.error("Session already present");
			return list.get().getSessionId();
		} else {
//			loginSessionrepository.deleteRecords(username);
			Calendar date = Calendar.getInstance();
			System.out.println("Current Date and TIme : " + date.getTime());
			long timeInSecs = date.getTimeInMillis();
			Date expiryDt = new Date(timeInSecs + (10 * 60 * 1000));
			System.out.println("After adding 10 mins : " + expiryDt);
			LoginSession session = new LoginSession();
			String uid = UUID.randomUUID().toString();
			session.setUsername(username);
			session.setSessionId(uid);
			session.setLoginDT(new Date());
			session.setExpiryDT(expiryDt);
			LoginSession saveSession = loginSessionrepository.save(session);
			return saveSession.getSessionId();
		}
		
	}

}
