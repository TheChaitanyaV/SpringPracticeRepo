package com.springrestjpa.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springrestjpa.demo.model.Employee;
import com.springrestjpa.demo.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	private EmployeeService empService;

	public EmployeeController(EmployeeService empService) {
		super();
		this.empService = empService;
	}
	
	
	//build create employee rest api
	@PostMapping()
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		return new ResponseEntity<Employee>(empService.saveEmployee(employee), HttpStatus.CREATED);
		
	}
	
	//build all employees REST API
	@GetMapping
	public List<Employee> getAllEmployees(){
		return empService.getAllEmployees();
	}
	
	//build employee by ID AP/api/employees/1I
	//http:localhost:8080
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long empid) {
		return new ResponseEntity<Employee>(empService.getEmployeeById(empid), HttpStatus.OK);

	}
	
	//build update Employee rest API
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee( @PathVariable("id") long empid,@RequestBody Employee employee){
		return new ResponseEntity<Employee>(empService.updateEmployee(employee, empid),HttpStatus.OK);
		
	}
	
	//delete the Employee record by Id
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteEmployee( @PathVariable("id") long empid){
		empService.deleteEmployee( empid);
		return new ResponseEntity<String>("Employee deleted successfully",HttpStatus.OK);
	}
	
	
}
