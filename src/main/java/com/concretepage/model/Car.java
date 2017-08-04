package com.concretepage.model;


import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
@ManagedBean
@SessionScoped
@Entity
@Table(name = "cars")
public class Car implements Serializable {
 
    private static final long serialVersionUID = 1715935052239888761L;
    
    @Column
    private String cname;
    @Column
    private String color;
    
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
    private Long Id;
	
	@Column
    private String model;
	@Column
    private String regno;
 
    public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public Car() {
    }
 
    public Car(String cname, String color, Long Id, String model, String regno) {
        this.cname = cname;
        this.color = color;
        
        
        this.Id = Id;
        this.model = model;
        this.regno = regno;
    }
 
    public String getColor() {
        return color;
    }
 
    public void setColor(String color) {
        this.color = color;
    }
 
    public String getCname() {
 
        System.out.println("car name is" + cname);
        return cname;
    }
 
    public void setCname(String cname) {
        this.cname = cname;
    }
 
    public String getRegno() {
        return regno;
    }
 
    public void setRegno(String regno) {
        this.regno = regno;
    }
 
    public String getModel() {
        return model;
    }
 
    public void setModel(String model) {
        this.model = model;
    }
 

 
}