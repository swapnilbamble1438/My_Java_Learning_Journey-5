package com.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController   // @RestController has @ResponseBody inbuilt
public class MyRestController {
	
	@GetMapping("/welcome/{yourName}")
	public String greet(@PathVariable("yourName") String yourName)
	{
		return "Welcome " + yourName;
	}

}
