package com.service;

import java.util.List;

import com.model.CustomUsers;

public interface UsersDao {
	
	public List<CustomUsers> findUserByUserName(String name);

	
}
