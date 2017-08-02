package com.concretepage.model;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Entity
@Table(name = "nationality")
@ManagedBean(name = "nationality", eager = true)
@RequestScoped
@Component
public class Nationality {
	
	@Id
	@GeneratedValue
	@Column(name = "nationality_id")
	private long id;
	
	private String nationality  ;
	
	@ManyToOne
	@JoinColumn(name="student_id")
	private Student student  ; 
	

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Nationality(String nationality, Student student) {
		super();
		this.nationality = nationality;
		this.student = student;
	}

	public Nationality() {
		super();
		
	}

	@Override
	public String toString() {
		return "Nationality [id=" + id + ", nationality=" + nationality
				+ ", student=" + student + "]";
	} 
	
	public void addNationality(){
		
		System.err.println("its works nationality " + this.getId() );
	}
	

}
