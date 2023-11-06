  package com.config;

import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@EnableWebSecurity
public class MySecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private MyAuthenticationLoggerFilter myFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		
		
		/* we are not using this in this project
		auth
			.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select username,password,enabled from customusers where username=?") // here we are loading the data from customtable
			.authoritiesByUsernameQuery("select username,authority from customauthorities where username=?")
			.passwordEncoder(passwordEncoder);
		*/
		
		auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder);
		
		
		 
	
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http
		.authorizeRequests()
		.antMatchers("/hii","/admin").hasAnyAuthority("ADMIN","ROLE_ADMIN")
		.antMatchers("/bye","/normal").hasAnyAuthority("NORMAL","ROLE_NORMAL")
		.antMatchers("/helloworld").permitAll()
		.and() 
		.formLogin().loginPage("/myLogin").loginProcessingUrl("/doLogin") 
		.usernameParameter("username").passwordParameter("password")
		.and()
		.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.and()
		.httpBasic() 
		.and()
		.exceptionHandling().accessDeniedPage("/accessdenied")
		.and()
		.addFilterBefore(new MyAuthenticationLoggerFilter(), BasicAuthenticationFilter.class)  
//		.addFilterBefore(myFilter, UsernamePasswordAuthenticationFilter.class)   // you can also use this instead of upper line
		;
	}
	
	
	
	
	
}
