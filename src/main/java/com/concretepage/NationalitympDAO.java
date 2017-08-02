package com.concretepage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.sql.DataSource;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.concretepage.dao.NationalityImpl;
import com.concretepage.model.Nationality;
import com.concretepage.model.Student;


public class NationalitympDAO {
	
	
	@Autowired
    private SessionFactory sessionFactory;
	
	

	
	public NationalitympDAO(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;
	}


	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
   public void persist(Nationality na) {
		Session st = sessionFactory.getCurrentSession() ; 
		na.setStudent(null);
		st.merge(na);
		st.merge(na);
        st.flush();
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
