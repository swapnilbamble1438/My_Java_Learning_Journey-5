package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.model.Authorities;
import com.model.Users;
import com.service.UsersAuthoritiesDao;

@Controller
public class SignupController {
	
	@Autowired
	private UsersAuthoritiesDao uadao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/signup")
	public String signup()
	{
		return "signup";
	}
	
	@PostMapping("/doSignup")
	public String  doSignup(Model m,@ModelAttribute Users user,HttpServletRequest request)
	{
		String page = "";
		
		String role1 = request.getParameter("role1");
		String role2 = request.getParameter("role2");
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setEnabled(1);
		
		System.out.println("user: " + user);
		System.out.println("role1: " + role1);
		System.out.println("role2: " + role2);
		
		if((role1 == null || role1 == "") && (role2 == null || role2 == ""))
		{
			m.addAttribute("msg", "Select any role");
			page = "signup";
		}
		else
		{
			Authorities auth = new Authorities();
			
			if(uadao.insertuser(user) > 0)
			{
				if(role1 != null || role1 != "")
				{
					auth.setUsername(user.getUsername());
					auth.setAuthority(role1);
					uadao.insertauthority(auth);
				}
				if(role2 != null || role2 != "")
				{
					auth.setUsername(user.getUsername());
					auth.setAuthority(role2);
					uadao.insertauthority(auth);
				}
				m.addAttribute("msg", "Registration successful, Now you can login");
				page = "login";
			}
			else {
				m.addAttribute("msg","Registration Failed, Same username already exist, Please try some different User Name");
				page = "signup";
			}
		}
	
		return page;
	}
	
	

}
