package com.concretepage;


import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("com.concretepage")
public class StudentImpDAOConf {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	@Bean(name="StudentImpDAO")
	public StudentImpDAO getStudentImpDAOiNSTANCE(){
		return new StudentImpDAO(sessionFactory) ; 
	}
}
