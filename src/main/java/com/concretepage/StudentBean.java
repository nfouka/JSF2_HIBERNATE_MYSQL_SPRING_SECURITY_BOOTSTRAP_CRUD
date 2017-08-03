package com.concretepage;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.concretepage.model.Nationality;
import com.concretepage.model.Student;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;


@ManagedBean(name = "studentBean", eager = true)
@ViewScoped
@Component
public class StudentBean {
	private String name;
	private Integer id;
	
	@Autowired
	public StudentService userService;
	@Autowired
	public StudentImpDAO studentImpDAO ; 

	@Autowired
	public Nationality nationality ; 
	@Autowired
	public NationalitympDAO kaka ; 

	
	private Student selectedCar;
	private List<Student> student ;
	
	
    public Student getSelectedCar() {
		return selectedCar;
	}

	public void setSelectedCar(Student selectedCar) {
		this.selectedCar = selectedCar;
	}

	public List<Student> getSelectedCars() {
		return selectedCars;
	}

	public void setSelectedCars(List<Student> selectedCars) {
		this.selectedCars = selectedCars;
	}

	private List<Student> selectedCars;
	
	
	public List<Student> getList(){
		return studentImpDAO.list() ; 
	}
	
	public void redirect() throws IOException{
		
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		context.redirect(context.getRequestContextPath() + "/output.jsf" );
		System.out.print("redrect ok " + context.getRequestContextPath() + "/output.jsf");
		
	}
	
	
	 public void buttonAction(ActionEvent actionEvent) {
		    System.out.print(this.selectedCars);
		    for(Student s:selectedCars){
		    	//studentImpDAO.delete(s);
		    	init();
		    }
	        addMessage("Welcome to Primefaces!!");
	    }
	     
	    public void addMessage(String summary) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
	
	public void remove(int id) throws IOException{
		System.err.println("student has been removed stuent is" + id );
		studentImpDAO.delete(id);
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        FacesMessage msg = new FacesMessage("Car Selected   id :", ""+id );
        init() ; 
       
		context.redirect(context.getRequestContextPath() + "/output.jsf" );
	}
	
	public String fetchStudent(){
		name = userService.getStudent(id); 
		
		studentImpDAO.list() ; 
		return "output";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Car Selected", ((Student) event.getObject()).getEmail());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
 
    public void onRowUnselect(UnselectEvent event) {
        FacesMessage msg = new FacesMessage("Car Unselected", ((Student) event.getObject()).getFirstName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

	@PostConstruct
	public void init() {
	
		
		for(int i=0 ; i< 10 ; i++) {
			Student st2 = new Student("Amine "+ Math.random(), "Ahmed "+ Math.random(), "nadir.fouka@"+Math.random()+".com", "+213 85 96 7850 80") ; 
						studentImpDAO.persist(st2);
			}
		student = studentImpDAO.list() ; 
		
	}
	
	
	private String logger ; 
	
    public String getLogger() {
    	
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	String namePrincipalRol = null;
    	if (auth instanceof AnonymousAuthenticationToken) {
    	    namePrincipalRol = "ROLE_ANONYMOUS";
    	} else {
    	    namePrincipalRol = auth.getAuthorities().iterator().next().getAuthority();
    	}
    	
    	UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	
		return userDetails.getUsername() ; 
	}
	public void setLogger(String logger) {
		this.logger = logger;
	}
	
	
	 public void showMessage() {
	        RequestContext.getCurrentInstance().showMessageInDialog(new FacesMessage(FacesMessage.SEVERITY_INFO, "What we do in life", "Echoes in eternity."));
	    }
	
	 public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}
	
	
	
	public void addNationality(){
		kaka.persist(nationality);
		System.err.println("its works nationality ");
	}
	

	public void destroyWorld() {
	        addMessage("System Error", "Please try again later.");
	    }
	     
	    public void addMessage(String summary, String detail) {
	        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
	        
	        
	        
	        FacesContext.getCurrentInstance().addMessage(null, message);
	    }
}