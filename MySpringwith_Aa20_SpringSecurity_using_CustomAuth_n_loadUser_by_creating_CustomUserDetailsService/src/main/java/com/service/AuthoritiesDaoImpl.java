package com.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import com.model.CustomAuthorities;
import com.model.CustomUsers;

@Repository
public class AuthoritiesDaoImpl implements AuthoritiesDao{

	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@Override
	public ArrayList<GrantedAuthority> findAuthorityByUserName(String username) {
		
		
		
		String sql = "select * from customauthorities where username=?";
		
			
		List<CustomAuthorities> customauthorities =	jdbcTemplate.query(sql,new BeanPropertyRowMapper<CustomAuthorities>(CustomAuthorities.class),username);
			
		ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	
		
		customauthorities.forEach(authority->{
			authorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
		});
		
			return authorities;
	}

}
