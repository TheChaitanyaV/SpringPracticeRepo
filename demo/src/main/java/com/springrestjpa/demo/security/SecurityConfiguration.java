package com.springrestjpa.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
		
	}
	
	@Override 
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		//in-memory authentication is one way of handling authentication where the username,pwd, role are saved in memory i.e.,
		//perform validation until the spring server is running. Once the server is stopped, memory is cleared out and cannot perform validation.
		// to add multiple users , we can use and() method i.e., method chaining paradigm
		auth.inMemoryAuthentication().withUser("root").password("blah123").roles("USER")
									.and().withUser("foo").password("foo123").roles("ADMIN");
		
		//Always deal with hashed passwords instead of clear text passwords
		//here we create passwordencoder bean and encode the password
		
	}
	
	@Override 
	public void configure(HttpSecurity http) throws Exception{
		//the below hasRole ensures only USER role credentials work on the path mentioned- httpBasic specifies that the basic authentication to be used.
		//can also use formlogin instead which generates default login page 
		//if any other role credentials are used, Forbidden response is thrown
		//to allow any kind of access, permitAll() method is used 
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/employees/**").hasRole("ADMIN")
								.antMatchers("/api/employees/**").hasRole("USER").and().httpBasic();
		//the ADMIN can access only POST endpoint with URI /api/employees/
		
		//Spring Security enables CRSF(Cross Site Request Forgery) protection. Because of this, we can access only GET endopoints.
		//So to access PUT, POST, DELETE endpoints, we have to disable CSRF protection. below code disables crsf
		http.csrf().disable();
		
		
	}

}
