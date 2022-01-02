package com.springboot.employee.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.employee.exception.ResourceNotFoundException;
import com.springboot.employee.model.EmployeeDTO;
import com.springboot.employee.repository.EmployeeRepository;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	

	@GetMapping("/employees")
	public List<EmployeeDTO> employees()
	{
		return this.employeeRepository.findAll();
	}
	
	
	@PostMapping("/employees")
	public EmployeeDTO createEmployee(@Validated @RequestBody EmployeeDTO employeeDTO) {
		
		System.out.print(employeeDTO.getDateOfJoining()+"==== Date of joining");
			return employeeRepository.save(convertToEntity(employeeDTO));
	}
	
	@GetMapping("/employees/empIdAndName")
	public ResponseEntity<EmployeeDTO> getEmployeeByIdAndName(@RequestParam int employeeNo, 
				@RequestParam String employeeName) {
		
		System.out.print("Inside getDetails");
		return new ResponseEntity<EmployeeDTO>(employeeRepository.findByEmployeeNoAndEmployeeName(employeeNo, employeeName), HttpStatus.OK);
	}
	
	@GetMapping("employees/{employeeNo}")
    public ResponseEntity<EmployeeDTO> getById(@PathVariable int employeeNo) {
        Optional<EmployeeDTO> employeeDTO = employeeRepository.findById(employeeNo);
            return new ResponseEntity<>(employeeDTO.get(), HttpStatus.OK);
        
    }
	
		
	@PutMapping("/employees/{employeeNo}")
	public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable(value = "employeeNo") int employeeNo,
	   @RequestBody EmployeeDTO employeeDetails) throws ResourceNotFoundException {
	     EmployeeDTO employeeDTO = employeeRepository.findById(employeeNo)
	     .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this Employee No " + employeeNo));

	     EmployeeDTO updatedEmployee = employeeRepository.save(employeeDTO);
	     return ResponseEntity.ok(updatedEmployee);
	}
	
	@DeleteMapping("/employees/{employeeNo}")
    public Map<String, Boolean> deleteEmployee(@PathVariable(value = "employeeNo") int employeeNo)
         throws ResourceNotFoundException {
        EmployeeDTO employee = employeeRepository.findById(employeeNo)
       .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this Employee No : " + employeeNo));

        employeeRepository.delete(employee);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
	
	
	public EmployeeDTO convertToEntity(EmployeeDTO employeeDTO) {
		EmployeeDTO employeeDTO2 = modelMapper.map(employeeDTO, EmployeeDTO.class);
		return employeeDTO2;
	}


}
