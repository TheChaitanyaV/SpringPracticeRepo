package com.springrestjpa.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springrestjpa.demo.model.Employee;

//@Repository annotation need not be added as JpaRepository interface(SimpleJpaRepository)
//internally provides @repository annotation
public interface EmployeeRepository extends JpaRepository<Employee , Long> {

}
