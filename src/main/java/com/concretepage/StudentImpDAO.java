package com.concretepage;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.concretepage.model.Nationality;
import com.concretepage.model.Student;


public class StudentImpDAO{
	
	 	
	 	 @Autowired
	     private SessionFactory sessionFactory;

		
		public SessionFactory getSessionFactory() {
			return sessionFactory;
		}
		
		

		public StudentImpDAO(SessionFactory sessionFactory) {
			super();
			this.sessionFactory = sessionFactory;
		}



		public void setSessionFactory(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}

		@Transactional
	    public void persist(Student student) {
			Session st = sessionFactory.getCurrentSession() ; 
			Nationality n1 = new Nationality("ALGERIAN", student) ; 
			Nationality n2 = new Nationality("FRANCE", student) ; 
			Nationality n3 = new Nationality("SAUDIA", student) ; 
			Nationality n4 = new Nationality("USA", student) ; 
			st.persist(student);
			st.persist(n1); 
			st.persist(n2);
			st.persist(n3); 
			st.persist(n4);
			st.flush();
			
	        	
	    }
		
		@Transactional
	    public void delete(int id) {
			Session st = sessionFactory.getCurrentSession() ; 
			String queryForRemoveNatinality  = "DELETE FROM `nationality` WHERE `nationality`.`student_id` = " + id ; 
			String queryForRemoveStudent     = "DELETE FROM `student` WHERE `student`.`student_id` = "  + id  ; 
			
			SQLQuery query1 =sessionFactory.getCurrentSession().createSQLQuery(queryForRemoveNatinality); query1.executeUpdate()  ; 
			SQLQuery query2 =sessionFactory.getCurrentSession().createSQLQuery(queryForRemoveStudent);    query2.executeUpdate()  ; 
	        st.flush();
	        FacesMessage msg = new FacesMessage("Car Selected   id :", ""+id );
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
		
		
		
	    
		@Transactional
	    public List list() {
	        @SuppressWarnings("unchecked")
	        String sql = "select s.student_id as id , nationality_id,nationality,email,first_name as fn,last_name as ln,phone from nationality n left join student s on n.student_id = s.student_id";
	        SQLQuery query =sessionFactory.getCurrentSession().createSQLQuery(sql);
	        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
	        List results = query.list();
	        return results ; 
	    }
	   
}
