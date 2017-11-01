package com.kenny.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
			throws Exception {
		// TODO: get username and password from mongoDB
		auth.inMemoryAuthentication().withUser("Kenny").password("123")
				.roles("USER", "ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// make /login route public
		http.authorizeRequests().antMatchers("/login").permitAll()
		// protect all other routes
		// only allow access from users with role user
				.antMatchers("/", "/*tweet*/**").access("hasRole('USER')").and()
				.formLogin();
	}
}
