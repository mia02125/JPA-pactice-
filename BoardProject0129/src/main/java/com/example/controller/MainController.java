package com.example.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.repository.UserRepository;

@Controller
public class MainController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private UserRepository userRepository; 
	
	@GetMapping("/")
	public String index() { 
		return "index";
	}
	
	@GetMapping("/join")
	public String join() { 
		return "join";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/find")
	public String find() { 
		return "find";
	}
	@GetMapping("/logout/{Id}") 
	public String logout() {
		session.invalidate();
		return "index"; //UserController에 mapping된게 없어서 404오류 발생 
	}	
}