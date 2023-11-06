// this class has no use in this project
// it is made just for showing functions of
// AuthenticationProvider interface

package com.config2;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class ThumbAuthenticationProvider implements AuthenticationProvider{

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		

		// write your own logic to authenticate
		// the user by using the user's thumb impression
		return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {

		return authentication.equals(ThumbAuthenticationProvider.class);
	}

}
