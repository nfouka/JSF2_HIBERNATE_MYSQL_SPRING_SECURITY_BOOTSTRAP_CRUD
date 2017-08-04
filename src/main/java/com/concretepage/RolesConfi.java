package com.concretepage;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("com.concretepage")
public class RolesConfi {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Bean(name="RolesConfi")
	public RolesImplDAO getStudentImpDAOiNSTANCE(){
		return new RolesImplDAO(sessionFactory) ; 
	}
}

