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
	
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		System.out.println("admin : "+ passwordEncoder.encode("admin"));
		System.out.println("normal : "+ passwordEncoder.encode("normal"));
		System.out.println("both : "+ passwordEncoder.encode("both"));
		
		
		auth
		.inMemoryAuthentication()
		.withUser("admin")
		.password(passwordEncoder.encode("admin"))//admin
		.roles("admin")
		.and()
		.withUser("normal")
		.password(passwordEncoder.encode("normal"))
		.roles("normal");
		

		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
		.authorizeRequests()
		.antMatchers("/hii","/admin").hasRole("admin")
		.antMatchers("/bye","/normal").hasRole("normal")
		.antMatchers("/helloworld").permitAll()
		.and() 
		.formLogin().loginPage("/myLogin").loginProcessingUrl("/doLogin") 
		.and()
		.logout()
		.and()
		.httpBasic() 
		;
	}
	
	
	
	
	
}
