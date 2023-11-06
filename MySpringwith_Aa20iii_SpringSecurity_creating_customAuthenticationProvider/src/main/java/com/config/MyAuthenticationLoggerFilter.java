package com.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.http.HttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class MyAuthenticationLoggerFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException 
	{
		
		String username = request.getParameter("username");
		System.out.println("username from MyAuthenticationLoggerFilter: " + username);
		
		
		// securitycontext will get user authentication details/credentials
	Authentication userAuthentication =	SecurityContextHolder.getContext().getAuthentication();
			
		if(userAuthentication != null)
		{
			System.out.println("username: " +userAuthentication.getName());
			System.out.println("authorities: " + userAuthentication.getAuthorities());
		}
		
	
		chain.doFilter(request, response);
			
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}





	

	
}
