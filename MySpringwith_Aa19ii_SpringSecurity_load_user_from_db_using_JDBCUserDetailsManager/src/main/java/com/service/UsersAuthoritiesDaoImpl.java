package com.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;import org.springframework.jdbc.core.PreparedStatementSetter;
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
		
	//------------------------------------------------------------------------------------------------------------		
			// 1st way of saving user
			/*
		 result = this.jdbcTemplate.update(query,user.getUsername(),user.getPassword(),user.getEnabled());
		*/
		// 1st way end
	//-------------------------------------------------------------------------------------------------------------		
		// 2nd way of saving user
		/*	
		result=	this.jdbcTemplate.update(query, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {

					ps.setString(1,user.getUsername());
					ps.setString(2, user.getPassword());
					ps.setInt(3, user.getEnabled());
					
				}
			});
			*/
			
		// 2nd way end	
	//--------------------------------------------------------------------------------------------------------------		
		// 3rd way of saving user
		
				result=	this.jdbcTemplate.update(query,  ps-> {
						ps.setString(1,user.getUsername());
									ps.setString(2, user.getPassword());
									ps.setInt(3, user.getEnabled());
					});
			
		// 3rd way end		
	//-------------------------------------------------------------------------------------------------------------		
						
			
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
