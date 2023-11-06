package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
	
	@GetMapping("/accessdenied")
	public String accessDenied()
	{
		return "accessdenied";
	}

}
