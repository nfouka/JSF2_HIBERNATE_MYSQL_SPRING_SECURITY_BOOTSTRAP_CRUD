package com.concretepage;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

import com.concretepage.model.Student;


@Configurable
public class StudentConf {
	@Bean
	public Student getInstanceStudent(){
		return new Student("xx", "yy", "xx@voila.fr", "00238152063") ; 
	}
}
