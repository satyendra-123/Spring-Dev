package com.it.test.nkia.app.config;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Value("${api.user}")
	private String apiUser;
	
	@Value("${api.password}")
	private String password;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {	
			http.authorizeRequests().anyRequest().authenticated().and().httpBasic().and().csrf().disable();	
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.inMemoryAuthentication().withUser(new String(Base64.getDecoder().decode(apiUser)))
					.password("{noop}"+new String(Base64.getDecoder().decode(password))).roles("USER");
	}
}
