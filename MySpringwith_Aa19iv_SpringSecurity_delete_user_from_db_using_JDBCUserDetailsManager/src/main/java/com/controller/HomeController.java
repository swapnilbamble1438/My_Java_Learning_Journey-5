package com.controller;

import java.security.Principal;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

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
	
	@Autowired
	JdbcUserDetailsManager userDetailsManager;
	
	
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
	
	
	// deleting user using JDBCUserDetailsManager
	@GetMapping("/deleteUser")
	public String deleteUser(@RequestParam("username")String username,Principal prin, Authentication auth,Model m,HttpServletRequest request)
	{
		if(!username.equals("admin") ||
		   !username.equals("normal") ||
		   !username.equals("both"))
		{	
			userDetailsManager.deleteUser(username);
		}	
		
		m.addAttribute("msg"," Account with Username: <b>"+username +"</b> Deleted Successfully..");
				
		return "redirect:/logout";
		
	}

}
