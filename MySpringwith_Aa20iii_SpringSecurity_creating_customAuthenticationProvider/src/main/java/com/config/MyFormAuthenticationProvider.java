package com.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.model.CustomUsers;
import com.service.AuthoritiesDao;
import com.service.UsersDao;

@Component
public class MyFormAuthenticationProvider implements AuthenticationProvider{

	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthoritiesDao authoritiesDao;
	
	
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		// authentication logic to authenticate an user
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		List<CustomUsers> users = usersDao.findUserByUserName(username);
		
		if(!users.isEmpty())
		{
			
		CustomUsers user = users.get(0);
		
			if(passwordEncoder.matches(password, user.getPassword()))
			{
				// grant him the right roles
				// and create and authentication object
				// and return it
				
				List<GrantedAuthority> authorities = authoritiesDao.findAuthorityByUserName(username);
				
				
				return new UsernamePasswordAuthenticationToken(username,password,authorities);
				
				
			}else {
				
				
				System.out.println("Exception: Username or Password is wrong.");
				throw new UsernameNotFoundException("Username or Password is worng.");
			}
		
		
		
		}else
		{
			System.out.println("Exception: User with Username: "+username+" not found in database..");
			throw new UsernameNotFoundException("User with Username: "+username+" not found in database..");
		}
		
		
		
		
	}

	public boolean supports(Class<?> authentication) {

		// check if the authentication type that user
		// want to authenticate with supports or not
		
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
