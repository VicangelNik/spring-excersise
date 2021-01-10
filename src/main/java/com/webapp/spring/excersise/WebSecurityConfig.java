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

//		http.csrf().disable().authorizeRequests().antMatchers("/console/**")
//				.hasAnyAuthority("AUTHORITY_USER", "AUTHORITY_ROLE").antMatchers("/", "/home", "/employee/**")
//				.hasAnyAuthority("VIEW_CONFIRMED_PERMITS", "VIEW_PERMITS", "CONFIRM_PERMIT", "VIEW_REQUESTED_PERMITS",
//						"REQUEST_PERMIT")
//				.anyRequest().authenticated().and().formLogin().loginPage("/login").defaultSuccessUrl("/home", true)
//				.permitAll().and().logout().permitAll();
		http.csrf().disable().authorizeRequests()
				.antMatchers("/console/register_user", "/console/save_user", "/console/show_all", "/console/edit",
						"/console/edit/{id}", "/console/delete/{id}")
				.hasAuthority("AUTHORITY_USER")
				.antMatchers("/console/register_role", "/console/save_role", "/console/show_roles",
						"/console/edit_role", "/console/edit_role/{id}", "/console/delete_role/{id}")
				.hasAuthority("AUTHORITY_ROLE").antMatchers("/console")
				.hasAnyAuthority("AUTHORITY_USER", "AUTHORITY_ROLE")
				.antMatchers("/employee/view_permits", "/employee/view_permits/rest").hasAuthority("VIEW_PERMITS")
				.antMatchers("/employee/view_confirmed_permits", "/employee/view_confirmed_permits/rest")
				.hasAuthority("VIEW_CONFIRMED_PERMITS")
				.antMatchers("/employee/view_requested_permits", "/employee/view_requested_permits/rest")
				.hasAuthority("VIEW_REQUESTED_PERMITS").antMatchers("/employee/register_permit")
				.hasAuthority("REQUEST_PERMIT").antMatchers("/employee/confirm_permit", "/employee/confirm_permit/{id}")
				.hasAuthority("CONFIRM_PERMIT").antMatchers("/employee/save_permit")
				.hasAnyAuthority("REQUEST_PERMIT", "CONFIRM_PERMIT").antMatchers("/employee")
				.hasAnyAuthority("VIEW_CONFIRMED_PERMITS", "VIEW_PERMITS", "CONFIRM_PERMIT", "VIEW_REQUESTED_PERMITS",
						"REQUEST_PERMIT")
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

//	@Bean
//	@Override
//	public UserDetailsService userDetailsService() {
//		List<UserDetails> userDetailsList = new ArrayList<>();
//		userDetailsList
//				.add(User.withDefaultPasswordEncoder().username("user").password("password").roles("EMPLOYEE").build());
//		userDetailsList.add(User.withDefaultPasswordEncoder().username("admin").password("pass")
//				.roles("EMPLOYEE", "ADMIN").build());
//		userDetailsList.add(User.withDefaultPasswordEncoder().username("chief").password("pass")
//				.roles("EMPLOYEE", "CHIEF").build());
//		userDetailsList.add(User.withDefaultPasswordEncoder().username("manager").password("pass")
//				.roles("EMPLOYEE", "MANAGER").build());
//		userDetailsList.add(User.withDefaultPasswordEncoder().username("personel").password("pass")
//				.roles("EMPLOYEE", "PERSONEL").build());
//
//		return new InMemoryUserDetailsManager(userDetailsList);
//	}
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
}
