package com.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com")
public class MyAppConfig {
	
	
	@Bean
	InternalResourceViewResolver  viewResolver()
	{
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}

	
	@Bean
	PasswordEncoder  getNoOPasswordEncoder()
	{
		return NoOpPasswordEncoder.getInstance();
	}
	
//	@Bean
	PasswordEncoder getBCryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean("ds")
	public DataSource getDataSource()
	{
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/springmvcsecurity");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("root");
		driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		
		return driverManagerDataSource;
	}
	
	@Bean("jdbcTemplate")
	public JdbcTemplate getTemplate()
	{
		JdbcTemplate jdbcTemplate =  new JdbcTemplate();
		jdbcTemplate.setDataSource(getDataSource());
		return jdbcTemplate;
		
	}
	
	@Bean
	public JdbcUserDetailsManager jdbcUserDetailsManager()
	{
		JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(getDataSource());
		
		// these queries will override the original queries of JdbcUserDetailsManager
		// I have commented this lines for this project
		// thatswhy operation like adding,deleting and 
		// changing password of user will not work
//		userDetailsManager.setUsersByUsernameQuery("select username,password,enabled from customusers where username=?");
//		userDetailsManager.setAuthoritiesByUsernameQuery("select username,authority from customauthorities where username=?");
//		userDetailsManager.setChangePasswordSql("update customusers set password=? where username=? ");
//		userDetailsManager.setCreateUserSql("insert into customusers (username, password, enabled) values (?,?,?)");
//		userDetailsManager.setCreateAuthoritySql("insert into customauthorities (username, authority) values (?,?)");
//		userDetailsManager.setDeleteUserSql("delete from customusers where username = ?");
//		userDetailsManager.setDeleteUserAuthoritiesSql("delete from customauthorities where username = ?");
		
		return userDetailsManager;
	}

	
		
}
