package com.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.model.CustomUsers;
import com.service.AuthoritiesDao;
import com.service.UsersDao;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private AuthoritiesDao authoritiesDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	// do a db call with the username 
	// to check if the user is present inside the db
		List<CustomUsers> users = usersDao.findUserByUserName(username);
		
		if(users.isEmpty())
		{
			System.out.println("Exception: User with Username: "+username+" not found in database..");
			throw new UsernameNotFoundException("User with Username: "+username+" not found in database..");
		}
		CustomUsers user = users.get(0);
		List<GrantedAuthority> authorities = authoritiesDao.findAuthorityByUserName(username);
		
		return new User(user.getUsername(),user.getPassword(),authorities);

		
		/*// you can also use this way to create userdetails
		UserDetails userDetails = User.withUsername(user.getUsername())
				.password(user.getPassword())
				.authorities(authorities).build();
		
		
		return  userDetails;
		*/
	}

}
