package com.olx.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfigration extends WebSecurityConfigurerAdapter {

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	//Authentication
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {  //For Authentication
		// TODO Auto-generated method stub
		super.configure(auth);
		auth.userDetailsService(userDetailsService);
		
		/*
		 * auth.inMemoryAuthentication()
		 * .withUser("man").password(passwordEncoder.encode("man123")).roles("ADMIN")
		 * .and()
		 * .withUser("tom").password(passwordEncoder.encode("tom123")).roles("USER");
		 */
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception { //For Authorization
		// TODO Auto-generated method stub
		//super.configure(http);
		//http.authorizeRequests()
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/user").hasAnyRole("USER", "ADMIN")
		.antMatchers("/admin").hasRole("ADMIN")
		.antMatchers("/user/authenticate").permitAll()
		.and().formLogin();
	}
	
	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception {
		return super.authenticationManager();
	}
}
