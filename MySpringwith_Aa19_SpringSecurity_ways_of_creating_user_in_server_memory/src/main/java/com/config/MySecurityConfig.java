  package com.config;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private DataSource dataSource;

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		
// 	This is one way of providing userdetails  in InMemory Authentication	
/*		 auth
		 .inMemoryAuthentication()
		 .withUser("both")
		 .password(passwordEncoder.encode("both"))
		 .roles("ADMIN","NORMAL");
*/		
		
		// another way to create user
		
		ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		SimpleGrantedAuthority authority1 = new SimpleGrantedAuthority("ADMIN");
		SimpleGrantedAuthority authority2 = new SimpleGrantedAuthority("NORMAL");

		authorities.add(authority1);
		authorities.add(authority2);
		
		User yash = new User("yash@gmail.com","1234",authorities);
	
		
		// another way to create userdetails
		UserDetails pratikDetails = User.withUsername("pratik@gmail.com").password("1234").roles("ADMIN","NORMAL").build();
		
		
	
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
		.authorizeRequests()
		.antMatchers("/hii","/admin").hasAuthority("ADMIN")
		.antMatchers("/bye","/normal").hasAuthority("NORMAL")
		.antMatchers("/helloworld").permitAll()
		.and() 
		.formLogin().loginPage("/myLogin").loginProcessingUrl("/doLogin") 
		.and()
		.logout()
		.and()
		.httpBasic() 
		.and()
		.exceptionHandling().accessDeniedPage("/accessdenied")
		;
	}
	
	
	
	
	
}
