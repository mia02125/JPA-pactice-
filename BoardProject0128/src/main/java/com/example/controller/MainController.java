package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
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
}
