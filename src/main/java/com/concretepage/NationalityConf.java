package com.concretepage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.concretepage.model.Nationality;


@Configuration
public class NationalityConf {
	@Bean(name="NationalityConf")
	public Nationality getNationalityInstance(){
		return new Nationality() ; 
	}
}
