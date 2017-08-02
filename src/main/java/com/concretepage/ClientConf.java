package com.concretepage;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.concretepage.dao.Client;

@Configuration
@ComponentScan("com.concretepage")
public class ClientConf {
	@Bean
	public Client getInstanceClient(){
		return new Client() ; 
	}
}
