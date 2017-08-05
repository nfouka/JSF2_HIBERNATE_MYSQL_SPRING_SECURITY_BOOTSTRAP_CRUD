package com.concretepage;



import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
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
		
		@Transactional
	    public void delete(int id) {
			Session st = sessionFactory.getCurrentSession() ; 
			String queryForRemoveUsers  = "DELETE FROM `users` WHERE `users`.`user_id` = " + id ; 
			System.err.println(queryForRemoveUsers);
			
			SQLQuery query1 =sessionFactory.getCurrentSession().createSQLQuery(queryForRemoveUsers); 
			query1.executeUpdate()  ; 
	        st.flush();
	        FacesMessage msg = new FacesMessage("queryForRemoveUsers   id :", ""+id );
	        FacesContext.getCurrentInstance().addMessage(null, msg);
	    }
		
		
		
	    
		@Transactional
	    public List<User> list() {
	        String sql = "select user_id, email, enabled, password, phone, username,cv from users where enabled = 0 ";
	        SQLQuery query =sessionFactory.getCurrentSession().createSQLQuery(sql);
	        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
	        List<User> results = query.list() ; 
	        return results ; 
	    }


		@Transactional
		public void actvate(int id) {
			
			/*
			Session st = sessionFactory.getCurrentSession() ; 
			String queryForRemoveUsers  = "UPDATE `users` SET `enabled` = b'1' WHERE `users`.`user_id` = " + id ;			
			SQLQuery query1 =sessionFactory.getCurrentSession().createSQLQuery(queryForRemoveUsers); 
			query1.executeUpdate() ; 
	        st.flush();
		*/
			String sqlQuery = "UPDATE users SET enabled = 1 WHERE user_id = " + id ;		
	        System.err.println(sqlQuery);
	        Transaction transaction = null;
	        
	        	int result = -1;
	        	
	            Session session = sessionFactory.getCurrentSession();
	            transaction = session.beginTransaction();
	            SQLQuery query = session.createSQLQuery(sqlQuery);
	            result = query.executeUpdate();
	            transaction.commit();
	            
	        
		        
		}
		
		
	   
}
