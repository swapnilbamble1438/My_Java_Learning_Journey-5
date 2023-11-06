package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.model.CustomUsers;

@Repository
public class UsersDaoImpl implements UsersDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<CustomUsers> findUserByUserName(String username) {

		String sql = "select * from customusers where username=?";
		
	//	Object[] args = {username};
		
		
		
//	CustomUsers user =	jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<CustomUsers>(CustomUsers.class),username);  // if data not found this .queryForObject in return provide some value with exception 
	List<CustomUsers> users =	jdbcTemplate.query(sql,new BeanPropertyRowMapper<CustomUsers>(CustomUsers.class),username); // if data not found this .query in return provide null value, so it is good way
	
		
		return users;
	}

}
