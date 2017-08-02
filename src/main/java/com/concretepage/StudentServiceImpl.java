package com.concretepage;

import java.util.HashMap;
import java.util.Map;

public class StudentServiceImpl implements StudentService {
	Map<Integer, String> map = new HashMap<Integer, String>();
	{
		map.put(1, "Ram");
		map.put(2, "Shyam");
		map.put(3, "Rahim");
	}
	public String getStudent(Integer id) {
		System.out.println("Fetching student for the id:"+ id);
		if(map.get(id) != null){
			return map.get(id);
		}
		return "not found in map" ; 
	}
}
