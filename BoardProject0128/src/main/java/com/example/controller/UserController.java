package com.example.controller;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.repository.UserRepository;
import com.example.service.UserService;



@Controller
public class UserController {

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/joinRequest")
	public String join(@RequestParam Map<String, String> paramMap) { 
		String userId = paramMap.get("userId");
		String userPw = paramMap.get("userPw");
		String userName = paramMap.get("userName");
		String userEmail = paramMap.get("userEmail");
		String signUpDate = paramMap.get("signUpDate");
		String page = userService.join(userId, userPw, userName, userEmail, signUpDate);
		return page;
	}
	@PostMapping("/loginRequest")
	public String login(@RequestParam Map<String, String> paramMap) {
		String userId = paramMap.get("userId");
		String userPw = paramMap.get("userPw");
		String page = userService.login(userId, userPw);
		return page; 
	}
	@PostMapping("/findRequest")
	public String find(@RequestParam Map<String, String> paramMap) {
		String userName = paramMap.get("userName");
		String page = userService.find(userName);
		return page; 
		
	}
}
