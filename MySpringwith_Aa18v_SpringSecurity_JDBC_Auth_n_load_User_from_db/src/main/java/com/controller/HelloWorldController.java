package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloWorldController {
	
	
	@GetMapping("/helloworld")
	public String helloWorld()
	{
		return "helloworld";
	}
	
	@ResponseBody
	@GetMapping("/hii")
	public String hi()
	{
		return "hii";
	}
	
	@GetMapping("/admin")
	public String welcomeAdmin()
	{
		return "welcomeAdmin";
	}
	
	@ResponseBody
	@GetMapping("/bye")
	public String bye()
	{
		return "bye";
	}
	
	@GetMapping("/normal")
	public String welcomeNormal()
	{
		return "welcomeNormal";
	}

}
