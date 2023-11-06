// this class has no use in this project
// it is made just for showing functions of
// AuthenticationProvider interface

package com.config2;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		// write your own logic to authenticate
		// the user by using the user's name and password
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {

		
		return authentication.equals(UsernamePasswordAuthenticationProvider.class);
	}

}
