package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.model.Users;

@Controller
public class SignupController {
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/signup")
	public String signup()
	{
		return "signup";
	}
	
	@PostMapping("/doSignup")
	public String  doSignup(@ModelAttribute Users user,HttpServletRequest request,Model m)
	{
		String page = "";
		
		String role1 = request.getParameter("role1");
		String role2 = request.getParameter("role2");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEnabled(1);
		
		System.out.println("user: " + user);
		System.out.println("role1: " + role1);
		System.out.println("role2: " + role2);
		
		
		return "signup";
	}
	 
	

}
