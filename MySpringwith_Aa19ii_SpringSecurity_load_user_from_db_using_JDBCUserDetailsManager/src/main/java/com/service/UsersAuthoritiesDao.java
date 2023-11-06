package com.service;

import com.model.Authorities;
import com.model.Users;

public interface UsersAuthoritiesDao {

	public int insertuser(Users user);
	
	public int insertauthority(Authorities authority);
	
}
