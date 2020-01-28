package com.example.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.UserRepository;



@Service
public class UserService {

	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	
	@Autowired
	private HttpSession session; 
	
	@Autowired
	private UserPwHashCode userPwHashCode; 
	
	@Autowired
	private UserRepository userRepository;
	//회원가입
	public String join(String userId, String userPw, String userName, String userEmail, String signUpDate) {
		try {
			DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String currentDate = LocalDateTime.now().format(dateTimeFormat); 
			if(userId.equals("") || userPw.equals("") || userName.equals("") || userEmail.equals("")) {
				return "join";
			} else {
				User user = new User();
				user.setUserId(userId);
				String hashPw = userPwHashCode.getSHA256(userPw);
				user.setUserPw(hashPw);
				user.setUserName(userName);
				user.setUserEmail(userEmail);
				user.setSignUpDate(currentDate);
				userRepository.save(user);
			}	
		} catch (Exception e) {	
			e.printStackTrace();
			log.error("joinService Error");
		}
		return "index"; 
	}
	
	//로그인
	public String login(String userId, String userPw) {
		try {
			if(userId.equals("") || userPw.equals("")) {
				return "login";
			} else {
				String hashPw = userPwHashCode.getSHA256(userPw);
				User user = userRepository.findByUserIdAndUserPw(userId, hashPw);
				if(user == null) { 
					return "login";
				}
				session.setAttribute("loginUser", user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("loginService Error");
		}
		return "index"; 
	}
	
	//아이디 찾기
	public String find(String userName) {
		try {
			User[] user = userRepository.findByUserName(userName);
			if(user == null) { 
				return "find";
			} else {
				session.setAttribute("findUser", user);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("findService Error");
		}
		return "find"; 
		
	}
}
