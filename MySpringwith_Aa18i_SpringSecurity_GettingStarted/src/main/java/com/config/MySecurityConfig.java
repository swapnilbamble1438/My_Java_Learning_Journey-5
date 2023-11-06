package com.config;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter{
	
	// this class is going to help you 
	// to create the spring security filter chain
}
