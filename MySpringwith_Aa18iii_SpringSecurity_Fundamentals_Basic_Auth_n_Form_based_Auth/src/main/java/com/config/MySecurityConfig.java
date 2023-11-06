  package com.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	//  create some user details for an user
	// like username,password,role
	

	@Autowired
	private PasswordEncoder passwordEncoder;

	
	// first try only these configure method
	// default authentication contains both basic and form based login
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		System.out.println(passwordEncoder.encode("admin"));
		
		auth
		.inMemoryAuthentication()
		.withUser("admin")
		.password(passwordEncoder.encode("admin"))//admin
		.roles("admin");
		

		
	}
	
	// second try this method with the above method
	// Single authentication provides only basic or form based authentication at a time
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
		.authorizeRequests()
		.anyRequest()
	    .authenticated() // dont try it with .permitAll() .denyAll() // it will authenticate all requests
	//	.permitAll()  // dont try it with .authenticated() and .denyAll() // it will allow users to access all url's without any authentication
	 //	.denyAll() // dont try it with .authenticated() and .permitAll() // it will reject users to access all the url's even if user's have correct username and password
		.and()
	//	.httpBasic(); // first try basic only
	//	.and() 
		.formLogin(); // then try form base login only without basic
		
	}
	
	
	

	
	
	
	
	
}
