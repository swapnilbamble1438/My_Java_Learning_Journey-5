package com.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;
import com.dto.ChangePassword;

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
	private JdbcUserDetailsManager userDetailsManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
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
		
//		m.addAttribute("msg"," Account with Username: <b>"+username +"</b> Deleted Successfully..");
				
		return "redirect:/logout";
		
	}
	
	
	// update or change Password
	@GetMapping("/changePassword")
	public String changePassword(Principal prin, Authentication auth,Model m,HttpServletRequest request)
	{
		m.addAttribute("passwordDetails", new ChangePassword());
		
		return "changePassword";
	}
	
	// save updated/changed Password
	@PostMapping("/updatePassword")
	public String updatePassword(@ModelAttribute("passwordDetails") ChangePassword changePassword,Principal prin,Authentication auth,Model m)
	{
		String page="home";
		
		// JDBCUserDetailsManager has already got
		// method of changePassword
		// so we dont have to write our own logic
		// to changePassword
		// it will automatically change the password
		// of loggedin user
		
		
		if(!changePassword.getNewPassword().equals(changePassword.getConfirmPassword()))
		{
			m.addAttribute("msg","New Password and Confirm Password doesn't match");
			page = "changePassword";
		}
		else {
			
			//-- check whether the old password is correct ----------
			
			
			UserDetails user = userDetailsManager.loadUserByUsername(prin.getName());
			
		boolean matches =	passwordEncoder.matches(changePassword.getOldPassword(), user.getPassword());
			
			if(matches)
			{
				//--------------------------------------------------
	
				try {
					userDetailsManager.changePassword(	changePassword.getOldPassword(), passwordEncoder.encode(changePassword.getConfirmPassword()));
					
					m.addAttribute("msg","Password Successfully changed..");
					
				}
				catch (Exception e) {
					System.out.println(e);
					
					m.addAttribute("msg","Failed to change the Password, Something went wrong");
					page ="changePassword";
					
				}
				
			}	
			else {
				page ="redirect:/changePassword?invalidPassword";
			}
		}
		return page;
	}

}
