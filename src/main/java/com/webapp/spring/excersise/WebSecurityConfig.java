package com.webapp.spring.excersise;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests()
				// PATHS AVAILABLE FOR USER AUTHORITY
				.antMatchers("/console/register_user", "/console/save_user", "/console/show_all", "/console/edit",
						"/console/edit/{id}", "/console/delete/{id}")
				.hasAuthority("AUTHORITY_USER")
				// PATHS AVAILABLE FOR ROLE AUTHORITY
				.antMatchers("/console/register_role", "/console/save_role", "/console/show_roles",
						"/console/edit_role", "/console/edit_role/{id}", "/console/delete_role/{id}")
				// ONLY ROLE, USER AUTHORITIES access console
				.hasAuthority("AUTHORITY_ROLE").antMatchers("/console")
				.hasAnyAuthority("AUTHORITY_USER", "AUTHORITY_ROLE")
				// PATHS AVAILABLE FOR VIEW PERMIT
				.antMatchers("/employee/view_permits", "/employee/view_permits/rest").hasAuthority("VIEW_PERMITS")
				// PATHS AVAILABLE FOR VIEW CONFIRMED PERMITS
				.antMatchers("/employee/view_confirmed_permits", "/employee/view_confirmed_permits/rest")
				.hasAuthority("VIEW_CONFIRMED_PERMITS")
				.antMatchers("/employee/view_requested_permits", "/employee/view_requested_permits/rest")
				// PATHS AVAILABLE FOR REQUESTED PERMITS
				.hasAuthority("VIEW_REQUESTED_PERMITS").antMatchers("/employee/register_permit")
				// PATHS AVAILABLE FOR REQUEST PERMIT
				.hasAuthority("REQUEST_PERMIT").antMatchers("/employee/confirm_permit", "/employee/confirm_permit/{id}")
				// PATHS AVAILABLE FOR CONFIRM PERMIT
				.hasAuthority("CONFIRM_PERMIT").antMatchers("/employee/save_permit")
				// All authorities except user, role can access the employee path
				.hasAnyAuthority("REQUEST_PERMIT", "CONFIRM_PERMIT").antMatchers("/employee")
				.hasAnyAuthority("VIEW_CONFIRMED_PERMITS", "VIEW_PERMITS", "CONFIRM_PERMIT", "VIEW_REQUESTED_PERMITS",
						"REQUEST_PERMIT")
				// All authorities can access starting paths
				.antMatchers("/", "/home")
				.hasAnyAuthority("VIEW_CONFIRMED_PERMITS", "VIEW_PERMITS", "CONFIRM_PERMIT", "VIEW_REQUESTED_PERMITS",
						"REQUEST_PERMIT", "AUTHORITY_USER", "AUTHORITY_ROLE")
				.anyRequest().authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/home", true)
				.permitAll().and().logout().permitAll();

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	/**
	 * No password encoding is selected.
	 *
	 * @return
	 */
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
