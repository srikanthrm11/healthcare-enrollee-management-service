package com.healthcare.management.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.healthcare.management.dto.Enrollee;
import com.healthcare.management.service.EnrolleeService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = EnrolleeController.class)
public class EnrolleeControllerMockTest {
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private EnrolleeService enrolleeService;

	String exampleRequest = "{\r\n" + "	\"activationStatus\": false,\r\n" + "	\"phoneNumber\": \"515555555\",\r\n"
			+ "	\"dependents\": [{\r\n" + "		\"id\": \"d1\",\r\n" + "		\"name\": \"dependent1\",\r\n"
			+ "		\"birthDate\": \"today\"\r\n" + "	}, {\r\n" + "		\"id\": \"d2\",\r\n"
			+ "		\"name\": \"dependent2\",\r\n" + "		\"birthDate\": \"today2\"\r\n" + "	}],\r\n"
			+ "	\"id\": \"1\",\r\n" + "	\"name\": \"user1\",\r\n" + "    \"birthDate\":\"10-15-1982\"\r\n" + "}";

	@Test
	public void testAddEnrollee() throws Exception {

		Enrollee enrolle1 = new Enrollee();
		enrolle1.setId("1");
		enrolle1.setName("User1");
		enrolle1.setBirthDate("01-01-1990");
		enrolle1.setActivationStatus(true);
		enrolle1.setPhoneNumber("111-111-1111");
		Mockito.when(enrolleeService.addEnrolle(Mockito.any(Enrollee.class))).thenReturn(enrolle1);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/add/enrollee")
				.accept(MediaType.APPLICATION_JSON).content(exampleRequest).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
	
	@Test
	public void testRetrieveEnrolleeById() throws Exception {

		Enrollee enrolle1 = new Enrollee();
		enrolle1.setId("1");
		enrolle1.setName("User1");
		enrolle1.setBirthDate("01-01-1990");
		enrolle1.setActivationStatus(true);
		enrolle1.setPhoneNumber("111-111-1111");
		Mockito.when(enrolleeService.getEnrolleById(Mockito.any(String.class))).thenReturn(enrolle1);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/get/enrollee/1")
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	@Test
	public void testdeleteEnrolleeById() throws Exception {
		
	}

}
