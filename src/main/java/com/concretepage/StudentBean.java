package com.concretepage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.concretepage.model.Nationality;
import com.concretepage.model.Roles;
import com.concretepage.model.Student;
import com.concretepage.model.User;
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
	public RolesImplDAO rolesImplDAO ; 
	@Autowired
	public UserImpDAO userImpDAO ; 

	@Autowired
	public Nationality nationality ; 
	@Autowired
	public NationalitympDAO kaka ; 

	
	private Student selectedCar;
	private List<Student> student ;
	private String fileUpload ; 
	
	
	
	
    public String getFileUpload() {
		return fileUpload;
	}

	public void setFileUpload(String fileUpload) {
		this.fileUpload = fileUpload;
	}

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
	
		public Boolean loadData() {
		   return false   ; 
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
	
	public void validateUser(int id) throws IOException{
		System.err.println("user has been removed stuent is " + id );
		userImpDAO.delete(id);
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        FacesMessage msg = new FacesMessage("user Selected   id :", ""+id );
        
		context.redirect(context.getRequestContextPath() + "/secure/student.do" );
	}
	
	
	

	public void DeclineUser(int id) throws IOException{
		System.err.println("user has been updated stuent is " + id );
		userImpDAO.actvate(id);
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        FacesMessage msg = new FacesMessage("user Selected   id :", ""+id );
        
       
		context.redirect(context.getRequestContextPath() + "/secure/student.do" );
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
	
		
		for(int i=0 ; i< 20 ; i++) {
			Student st2 = new Student(
					new SessionIdentifierGenerator().nextSessionId() , 
					new SessionIdentifierGenerator().nextSessionId() , 
					new SessionIdentifierGenerator().nextSessionId()+ "@gmail.com",  "+33 85 96 7850 80") ; 
			
					 studentImpDAO.persist(st2);
			
		/*
		rolesImplDAO.persist(new Roles("nfouka", "ROLE_ADMIN"));
		
		userImpDAO.persist(new User( new SessionIdentifierGenerator().nextSessionId() , 
							new SessionIdentifierGenerator().nextSessionId() , 
							"nadir.fouka@gmail.com", "+3368556896452", false  ));
		}
		*/ 
		}
		rolesImplDAO.persist(new Roles("nfouka", "ROLE_ADMIN"));
		userImpDAO.persist(new User("nfouka", "nfouka", "", "", true));
		
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
	    
	    
	    
	    public void addnewUser(User user) {
	    	
	    	user.setEnabled(false);
	    	user.setCv(fileUpload);
	    	userImpDAO.persist(user);
	    	
	    	System.err.println(user.toString());
	    }
	    
	    
	    
	    public List<User> listUser() {
	    	
	    	List<User> l = userImpDAO.list() ; 
	    	return l ; 
	    }
	    
	    
	    public void fileUpload(FileUploadEvent event) throws IOException {
	        String path = FacesContext.getCurrentInstance().getExternalContext()
	                .getRealPath("/");
	        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddHHmmss");
	        String name = fmt.format(new Date())
	                + event.getFile().getFileName().substring(
	                      event.getFile().getFileName().lastIndexOf('.'));
	        
	        
	        path = "/home/nadir/Bureau/apache-tomcat-8.0.44/webapps/app/" ; 
	        File file = new File(path + "resources/demo/" + name);
	        System.out.println(path + "resources/demo/" + name);
	        fileUpload = path + "resources/demo/" + name ; 
	        InputStream is = event.getFile().getInputstream();
	        OutputStream out = new FileOutputStream(file);
	        byte buf[] = new byte[1024];
	        int len;
	        while ((len = is.read(buf)) > 0)
	            out.write(buf, 0, len);
	        is.close();
	        out.close();
	    }

	    
	    private DefaultStreamedContent download;
	    
	    
	    public void setDownload(DefaultStreamedContent download) {
	        this.download = download;
	    }

	    public DefaultStreamedContent getDownload() throws Exception {
	        System.out.println("GET = " + download.getName());
	        return download;
	    }

	    public void prepDownload(String file1) throws Exception {
	        File file = new File(file1);
	        InputStream input = new FileInputStream(file);
	        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
	        setDownload(new DefaultStreamedContent(input, externalContext.getMimeType(file.getName()), file.getName()));
	        
	    }
	    
	    
	    private StreamedContent file;

	    public void FileDownloadController(String cv) {        
	        InputStream stream = ((ServletContext)FacesContext.getCurrentInstance().getExternalContext().getContext()).getResourceAsStream(cv);
	        file = new DefaultStreamedContent(stream, "application/pdf", "downloaded_optimus.jpg");
	    }

	    public StreamedContent getFile() {
	        return file;
	    }  
	    
	    
	    
	    
}