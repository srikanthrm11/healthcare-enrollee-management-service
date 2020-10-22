package com.healthcare.management.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.healthcare.management.dto.Dependent;
import com.healthcare.management.dto.Enrollee;
import com.healthcare.management.dto.EnrolleeUpdate;
import com.healthcare.management.exception.ResourceNotFoundException;
import com.healthcare.management.service.EnrolleeServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EnrolleeServiceTest {

	@Autowired
	EnrolleeServiceImpl enrolleeServiceImpl;

	@Autowired
	EnrolleeRepository enrolleeRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		addEnrollees();
	}

	private void addEnrollees() {
		Enrollee enrolle1 = new Enrollee();
		enrolle1.setId("1");
		enrolle1.setName("User1");
		enrolle1.setBirthDate("01-01-1990");
		enrolle1.setActivationStatus(true);
		enrolle1.setPhoneNumber("111-111-1111");
		List<Dependent> dependents1 = new ArrayList<>();
		Dependent dependent1 = new Dependent();
		dependent1.setId("d1");
		dependent1.setName("Dependent1");
		dependent1.setBirthDate("01-01-2010");
		Dependent dependent2 = new Dependent();
		dependent1.setId("d2");
		dependent1.setName("Dependent2");
		dependent1.setBirthDate("01-01-2012");
		dependents1.add(dependent1);
		dependents1.add(dependent2);
		enrolle1.setDependents(dependents1);

		Enrollee enrolle2 = new Enrollee();
		enrolle2.setId("2");
		enrolle2.setName("User2");
		enrolle2.setBirthDate("01-01-1990");
		enrolle2.setActivationStatus(true);
		enrolle2.setPhoneNumber("111-111-1111");
		List<Dependent> dependents2 = new ArrayList<>();
		Dependent dependent3 = new Dependent();
		dependent3.setId("d1");
		dependent3.setName("Dependent1");
		dependent3.setBirthDate("01-01-2010");
		Dependent dependent4 = new Dependent();
		dependent4.setId("d2");
		dependent4.setName("Dependent2");
		dependent4.setBirthDate("01-01-2012");
		dependents2.add(dependent3);
		dependents2.add(dependent4);
		enrolle1.setDependents(dependents2);

		List<Enrollee> enrolleeList = new ArrayList<>();
		enrolleeList.add(enrolle1);
		enrolleeList.add(enrolle2);

		try {
			enrolleeList.stream().forEach(e -> enrolleeServiceImpl.addEnrolle(e));
		} catch (Exception e) {

		}

	}

	@Test
	public void addEnrolleTest() throws Exception {
		Enrollee enrolle1 = new Enrollee();
		enrolle1.setId("4");
		enrolle1.setName("User3");
		enrolle1.setBirthDate("01-01-1990");

		Enrollee enrolee = enrolleeServiceImpl.addEnrolle(enrolle1);
		Assert.assertNotNull(enrolee);

		System.out.print(enrolee.getName());

	}

	@Test
	public void getEnrolleTest() throws Exception {

		Enrollee enrollee = enrolleeServiceImpl.getEnrolleById("1");
		Assert.assertNotNull(enrollee);
		Assert.assertNotNull(enrollee.getId());
		Assert.assertEquals("1", enrollee.getId());
		Assert.assertNotNull(enrollee.getName());
		Assert.assertEquals("User1", enrollee.getName());

	}

	@Test
	public void deleteEnrolleTest() throws Exception {

		enrolleeServiceImpl.deleteEnrolle("1");

		try {
			enrolleeServiceImpl.getEnrolleById("1");
		} catch (Exception e) {
			Assert.assertTrue(e instanceof ResourceNotFoundException);
			Assert.assertTrue("Enrollee not found for provided enrollee Id".equals(e.getMessage()));
		}

	}

	@Test
	public void updateEnrolleTest() throws Exception {

		EnrolleeUpdate enrolleeUpdate = new EnrolleeUpdate();
		enrolleeUpdate.setId("2");
		enrolleeUpdate.setModifyEmployee(true);
		enrolleeUpdate.setName("updatedUser2");

		Enrollee enrollee = enrolleeServiceImpl.updateEnrolle(enrolleeUpdate);
		Assert.assertNotNull(enrollee);
		Assert.assertNotNull(enrollee.getId());
		Assert.assertEquals("2", enrollee.getId());
		Assert.assertNotNull(enrollee.getName());
		Assert.assertEquals("updatedUser2", enrollee.getName());

	}

}
