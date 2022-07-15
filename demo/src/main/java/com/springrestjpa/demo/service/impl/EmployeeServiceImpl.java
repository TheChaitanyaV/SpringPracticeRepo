package com.springrestjpa.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.springrestjpa.demo.exception.ResourceNotFoundException;
import com.springrestjpa.demo.model.Employee;
import com.springrestjpa.demo.repository.EmployeeRepository;
import com.springrestjpa.demo.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private EmployeeRepository emprepo;

	public EmployeeServiceImpl(EmployeeRepository emprepo) {
		super();
		this.emprepo = emprepo;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return this.emprepo.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return emprepo.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
		// TODO Auto-generated method stub
		/*
		 * Optional<Employee> employee = emprepo.findById(id); if(employee.isPresent())
		 * { return employee.get(); } else { throw new
		 * ResourceNotFoundException("Employee", "Id", id); }
		 */
		
		return emprepo.findById(id).orElseThrow(()->  new ResourceNotFoundException("Employee", "Id", id));
	}

	@Override
	public Employee updateEmployee(Employee employee, long id) {
		// we need to check if employee with given id exists or not
		Employee existingEmp = emprepo.findById(id).orElseThrow(()->  new ResourceNotFoundException("Employee", "Id", id));
		existingEmp.setFirstName(employee.getFirstName());
		existingEmp.setLastName(employee.getLastName());
		existingEmp.setEmail(employee.getEmail());
		emprepo.save(existingEmp);
		return existingEmp;
	}

	@Override
	public void deleteEmployee(long id) {
		// we need to check if employee with given id exists or not
		 Employee existingEmp = emprepo.findById(id).orElseThrow(()->  new ResourceNotFoundException("Employee", "Id", id));
		 emprepo.deleteById(id);
	}
}