package com.service;

import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;


public interface AuthoritiesDao {
	
	public ArrayList<GrantedAuthority> findAuthorityByUserName(String username);

}
