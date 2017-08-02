package com.concretepage.dao;

import java.util.List;
import com.concretepage.model.Nationality;

public interface NationalityImpl {
		public List<Nationality> listNationality() ; 
		public void add(Nationality nationality) ; 
		public void delete(Nationality nationality) ; 
}
