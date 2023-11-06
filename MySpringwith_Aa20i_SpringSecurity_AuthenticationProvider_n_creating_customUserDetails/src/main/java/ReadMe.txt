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



In config package
we have created three custom authentication provider
so It will check by which type user is authenticating
itself and provide that type of authentication provider
while authenticating 

so we can have different types of authentication provider 
we want.
and desired authentication provider will run according
to condition.


			                Authentication Provider
			                
Conditions			                						CustomAuthenticationProvider
			                
			
 username and Password authentication  --------------------------->   UsernamePasswordAuthenticationProvider
 
 Thumb impression authentication       --------------------------->   ThumbAuthenticationProvider
 
 Facecam authentication                --------------------------->   FaceCamAuthenticationProvider		



	Authentication Object
       / \                                   _        
	    |									|  AuthenticationProvider(I)                    
		|									|  DaoAuthenticationProvider(c) [DefaultAuthenticationProvider]           
Authentication -----> Authentication ------>|  UsernamePasswordAuthenticationProvider(c)   
    Filter	  <-----	 Manager     <----- |  ThumbAuthenticationProvider(c)              
		|									|_ FaceCamAuthenticationProvider(c)                                              
	    |			      									calls					
	   \ /           							   this two to authenticate the user
	  ContextHolder  				     				    /    \
					 									   /      \
					   									  /        \
											UserDetailsService     PasswordEncoder		
				
				
Authentication Provider check all customAuthenticationProvider
and select the one AuthenticationProvider which is required.
and that authentication Provider will call the 
UserDetailsService and PasswordEncoder to authenticate
the user.			
				

Process:
First request/httprequest (credentials) will come to specific authentication Filter.
AuthenticationFilter will convert the request/httprequest to
Authentication object. 
and that object will be send to authentication manager.
AuthenticationManager send that authentication object to 
Authentication Provider.
Now Authentication provider will validate this object. 
if the (credentials) username or password that its contains
is valid or not.
Authenticaion  provider
will choose the specific authentication provider
according to the condition for authentication.
and after authenticating the authentication object (using UserDetailsService)
if authentication failed it will throw exception (using UserDetailsService)
and if authentication successfull
, Authentication Provider
will send that authentication object to Authentication Manager and
and by Authentication Manager will send that object to Authentication Filter
and it will get saved to ContextHolder.
And once object is saved to ContextHolder user dont have to authenticate
again and the request does'nt need to go through from the same process again.


In this we have done username password authentication and
by default daoauthenticationprovider is working.