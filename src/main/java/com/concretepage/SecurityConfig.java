package com.concretepage;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;


@Configuration
@EnableWebSecurity
@Component
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource ds ; 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable();
		http.authorizeRequests().
		antMatchers("/secure/**").access("hasRole('ROLE_ADMIN')").
		
		and().formLogin().  //login configuration
        loginPage("/customLogin.do").
        loginProcessingUrl("/appLogin").
        usernameParameter("app_username").
        passwordParameter("app_password").
        defaultSuccessUrl("/secure/student.do").	
		and().logout().    //logout configuration
		logoutUrl("/appLogout"). 
		logoutSuccessUrl("/customLogin.do");

	} 
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		
		//auth.inMemoryAuthentication().withUser("nfouka").password("lonelyness").roles("ADMIN");
		
		
	
		final String findUserQuery = "select username,password,enabled "
				+ "from users " + "where username = ?";
		final String findRoles = "select username,role " + "from Roles "
				+ "where username = ?";
		
		auth.jdbcAuthentication().dataSource(ds)
				.usersByUsernameQuery(findUserQuery)
				.authoritiesByUsernameQuery(findRoles);

		
	}	
}  
