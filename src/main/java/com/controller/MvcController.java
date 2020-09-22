package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MvcController {

	@GetMapping("/signup1")
	public String signup() {
		return "Signup";
	}
	
	
	
}
