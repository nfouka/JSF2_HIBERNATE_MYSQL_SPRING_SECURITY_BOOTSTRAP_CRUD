package com.concretepage;



import javax.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.concretepage.model.User;


public class UserImpDAO{
	
	 	
	 	 @Autowired
	     private SessionFactory sessionFactory;

		
		public SessionFactory getSessionFactory() {
			return sessionFactory;
		}
		
		

		public UserImpDAO(SessionFactory sessionFactory) {
			super();
			this.sessionFactory = sessionFactory;
		}



		public void setSessionFactory(SessionFactory sessionFactory) {
			this.sessionFactory = sessionFactory;
		}

		@Transactional
	    public void persist(User user) {
			Session st = sessionFactory.getCurrentSession() ; 
			

			
			st.merge(user) ; 
			st.flush();
			
	        	
	    }
		
	   
}
