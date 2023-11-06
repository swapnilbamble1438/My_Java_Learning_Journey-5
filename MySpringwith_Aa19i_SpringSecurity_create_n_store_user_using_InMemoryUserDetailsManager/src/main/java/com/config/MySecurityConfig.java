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
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


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
		SimpleGrantedAuthority authority1 = new SimpleGrantedAuthority("ROLE_ADMIN");
		SimpleGrantedAuthority authority2 = new SimpleGrantedAuthority("ROLE_NORMAL");

		authorities.add(authority1);
		authorities.add(authority2);
		
		User both = new User("both",passwordEncoder.encode("both"),authorities);
	
		
		// another way to create userdetails
		UserDetails admin = User.withUsername("admin").password(passwordEncoder.encode("admin")).roles("ADMIN").build(); // by default this role is saving like ROLE_ADMIN
		UserDetails normal = User.withUsername("normal").password(passwordEncoder.encode("normal")).roles("NORMAL").build(); // by default this role is saving like ROLE_NORMAL
		
		// creating  user in our server memory using InMemoryUserDetailsManager Class
		// which implements UserDetailsManager Interface
		InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();
		inMemoryUserDetailsManager.createUser(both);
		inMemoryUserDetailsManager.createUser(admin);
		inMemoryUserDetailsManager.createUser(normal);
		
		auth
			.userDetailsService(inMemoryUserDetailsManager);
	
		
		// so we have done InMemoryAuthentication here
		// 
		// here loadUserByUsername method will verfiy 
		// the login details to do the login.
		// loadUserByUsername method is present in UserDetails interface
		// which is implemented by UserDetailsManager interface
		
		/*
		InMemoryUserDetailsManager(c)
		|						createUser(){}
		|                       deleteUser(){}
	    |                       updateUser(){}
	    |						updatePassword(){}                    
	    |                       loadUserByUsername(){}
	   \|/
	UserDetailsManager(I)  
	    |						createUser(){}
		|                       deleteUser(){}
	    |                       updateUser(){}
	    |						updatePassword(){}                    
	    |                       
	   \|/ 
	UserDetailsService(I)
	                           loadUserByUsername(){}
	                           
	    */                       
	
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
		.authorizeRequests()
		.antMatchers("/hii","/admin").hasAuthority("ROLE_ADMIN")
		.antMatchers("/bye","/normal").hasAuthority("ROLE_NORMAL")
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
