Add Dependencies

spring web mvc
servlet-api
jstl

spring jdbc
mysql connector

spring security taglibs

spring security-config 
security security web ( it should be same version as that of spring security config)

			
			
			AuthenticationProvider(I)
			DaoAuthenticationProvider(c)
			      calls
			     this two to authenticate the user
			     /    \
			 	/      \
			   /	    \
UserDetailsService     PasswordEncoder		



Try to login in this App	

In previous projects we are using
jdbc authentication we are loading user
using JdbcUserDetailsManager.
but in this project we are using custom 
authentication we are loading user by making
Custom UserDetailsManager.

