package com.springrtdemo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import com.sprinrtdemo.model.Employee;

@SpringBootApplication
public class SpringRestTemplateDemoApplication implements CommandLineRunner {
	private static final Log LOGGER = LogFactory.getLog(SpringRestTemplateDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringRestTemplateDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//execute each method sequentially - not all at a time
		
		// method to consume GET rest api with rest template
		getEmployeeDetails(1);

		// method to consume POST rest api with rest template

		Employee employee = new Employee();
		employee.setFirstName("KamalVerse");
		employee.setLastName("Vikram");
		employee.setEmail("vikraaam@gmail.com");
		addEmployee(employee);

		Employee employeeUpdate = new Employee();
		employeeUpdate.setId(5);
		employeeUpdate.setFirstName("KamalVerse");
		employeeUpdate.setLastName("Rolex");
		employeeUpdate.setEmail("vikraaam@gmail.com");
		updateEmployee(employeeUpdate);

		deleteEmployee(1);
	}

	public void getEmployeeDetails(long id) {
		String url = "http://localhost:8080/api/employees/{id}";
		RestTemplate restTemplate = new RestTemplate();
		Employee employee = restTemplate.getForObject(url, Employee.class, id);
		LOGGER.info("FirstName::" + employee.getFirstName() + "Email::" + employee.getEmail());
		LOGGER.info("\n");
	}

	public void addEmployee(Employee employee) {
		String url = "http://localhost:8080/api/employees/";
		RestTemplate restTemplate = new RestTemplate();
		Employee empresponse = restTemplate.postForObject(url, employee, Employee.class);
		LOGGER.info("FirstName::" + empresponse.getFirstName() + "Email::" + empresponse.getId());
		LOGGER.info("\n");
	}

	public void updateEmployee(Employee employee) {
		String url = "http://localhost:8080/api/employees/{id}";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(url, employee, employee.getId());
		LOGGER.info("Customer updated successfully");
		LOGGER.info("\n");
	}

	public void deleteEmployee(Integer empid) {
		String url = "http://localhost:8080/api/employees/{id}";
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(url, empid);
		LOGGER.info("Customer deleted successfully");
		LOGGER.info("\n");
	}

}
