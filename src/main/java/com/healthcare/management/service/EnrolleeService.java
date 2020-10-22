package com.healthcare.management.service;

import com.healthcare.management.dto.Enrollee;
import com.healthcare.management.dto.EnrolleeUpdate;

public interface EnrolleeService {
	
	public Enrollee getEnrolleById(String id);
	
	public Enrollee addEnrolle(Enrollee enrollee);
	
	public Enrollee updateEnrolle(EnrolleeUpdate eUpdate);
	
	public void deleteEnrolle(String id);

}
