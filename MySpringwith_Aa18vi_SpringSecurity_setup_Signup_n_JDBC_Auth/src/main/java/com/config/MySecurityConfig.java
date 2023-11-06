  package com.config;

import javax.sql.DataSource;

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
	
	@Autowired
	private DataSource dataSource;

	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		System.out.println(passwordEncoder.encode("admin"));
		
		// load the users info from the database
		// username, password and role
		
		auth
			.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(passwordEncoder);		
		
		
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
