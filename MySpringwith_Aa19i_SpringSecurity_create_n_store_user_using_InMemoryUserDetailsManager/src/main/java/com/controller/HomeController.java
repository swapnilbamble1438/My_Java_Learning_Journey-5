package com.controller;

import java.security.Principal;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	
	@ModelAttribute
	public void commonData(Principal prin, Authentication auth,Model m)
	{
		try {
			
		
			String username = prin.getName();
			
			Collection<? extends GrantedAuthority> userauthorities = auth.getAuthorities();
			
			m.addAttribute("username",username);
			m.addAttribute("userauthorities",userauthorities);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@GetMapping("/")
	public String home(Principal prin, Authentication auth,Model m)
	{
	
		return "home";
	}
	
	@ResponseBody
	@GetMapping("/hii")
	public String hi()
	{
		return "hii";
	}
	
	@GetMapping("/admin")
	public String welcomeAdmin(Principal prin, Authentication auth,Model m)
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
	public String welcomeNormal(Principal prin, Authentication auth,Model m)
	{
		return "welcomeNormal";
	}

}
