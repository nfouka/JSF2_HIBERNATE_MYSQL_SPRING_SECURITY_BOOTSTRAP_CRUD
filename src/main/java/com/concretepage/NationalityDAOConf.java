package com.concretepage;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan("com.concretepage")
public class NationalityDAOConf {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Bean(name="NationalitympDAO2")
	public NationalitympDAO geTNationalitympDAO(){
		return new NationalitympDAO(sessionFactory) ; 
	}
}
