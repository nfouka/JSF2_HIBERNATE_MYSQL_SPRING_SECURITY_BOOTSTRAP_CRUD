package com.concretepage;

public class client {
	
	private String name  ; 
	private String adress  ;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	} 

	public void SayHello(){
		System.out.println("hello from spring conf");
	}
}
