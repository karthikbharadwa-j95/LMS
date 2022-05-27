package com.te.lms.securityconfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@EnableWebSecurity
public class UserConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService detailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(detailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().and()
		.authorizeRequests()
		.antMatchers("/admin/**")
		.hasRole("ADMIN")
		.antMatchers("/mentor/**")
		.hasRole("MENTOR")
		.antMatchers("/employee/**")
		.hasRole("EMPLOYEE")
		.antMatchers("/employee/register").permitAll()
		.anyRequest().authenticated()
		.and()
		.formLogin();
	}

	@Bean
	public PasswordEncoder encode() {
		return NoOpPasswordEncoder.getInstance();
	}

}
