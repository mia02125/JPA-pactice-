package com.example.controller;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.model.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;



@Controller
public class UserController {

	private Logger log = LoggerFactory.getLogger(this.getClass());

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
	@RequestMapping(value = "/delete/{Id}", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@PathVariable Long Id) {
		try {
			User user = userRepository.findByUserId(Id);
			userService.delete(user.getId());	
		} catch (Exception e) {
			e.printStackTrace();
			log.error("deleteController Error");
		}
		return "redirect:/index"; 
	}
}
