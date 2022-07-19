package net.javaspringrestful.springboot.crudrestfulwebservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import net.javaspringrestful.springboot.crudrestfulwebservices.exception.ResourceNotFoundException;
import net.javaspringrestful.springboot.crudrestfulwebservices.model.Employee;
import net.javaspringrestful.springboot.crudrestfulwebservices.repository.EmployeeRepository;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	@PostMapping("/employees")
	
	public Employee createEmployee( @RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value="id") long employeeId) throws ResourceNotFoundException{
		Employee employee =employeeRepository.findById(employeeId).orElseThrow(()  -> new ResourceNotFoundException("Employee not found::" + employeeId));
		
				
		return ResponseEntity.ok().body(employee);
		
	}
}

