package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.model.Authorities;
import com.model.Users;


@Repository
public class UsersAuthoritiesDaoImpl implements UsersAuthoritiesDao{
	
	
@Autowired	
private JdbcTemplate jdbcTemplate;
	
	
	
	public int insertuser(Users user)
	{
		int result = 0;
		String query ="insert into Users(username,password,enabled) values(?,?,?)";
		try {
		
		 result = this.jdbcTemplate.update(query,user.getUsername(),user.getPassword(),user.getEnabled());
		
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int insertauthority(Authorities authority)
	{
		
		int result = 0;
		String query = "insert into Authorities(username,authority) values (?,?)";
		 
		try {
		 result = this.jdbcTemplate.update(query,authority.getUsername(),authority.getAuthority());
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}


}
