package com.service;

import com.model.CustomAuthorities;
import com.model.CustomUsers;

public interface UsersAuthoritiesDao {

	public int insertuser(CustomUsers user);
	
	public int insertauthority(CustomAuthorities authority);
	
}
