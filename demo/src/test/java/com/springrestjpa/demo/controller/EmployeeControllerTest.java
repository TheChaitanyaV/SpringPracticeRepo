package com.springrestjpa.demo.controller;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.springrestjpa.demo.model.Employee;
import com.springrestjpa.demo.service.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
public class EmployeeControllerTest {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	private EmployeeController empController;
	
	@Mock
	EmployeeService empService;
	
	@Before
	public void setUp() throws Exception {
		//creates mockmvc mock
		mockMvc = MockMvcBuilders.standaloneSetup(empController).build();
	}
	
	@Test
	public void testGetAllEmployees() throws Exception{
		List<Employee> empList = new ArrayList<Employee>();
		Employee emp = new Employee();
		emp.setFirstName("Rajan");
		emp.setLastName("Rayan");
		emp.setEmail("abc@xyz.com");
		emp.setId(1);
		empList.add(emp);
		Mockito.when(empService.getAllEmployees()).thenReturn(empList);
		mockMvc.perform(MockMvcRequestBuilders.get("/api/employees")).andExpect(status().isOk())
		.andExpect(jsonPath("$[0].id", CoreMatchers.is(1)));
	}
}
