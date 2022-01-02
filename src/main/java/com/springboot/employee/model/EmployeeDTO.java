package com.springboot.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="Employee_Registration")
public class EmployeeDTO {

	private int employeeNo;
	private String employeeName;
	private String dateOfJoining;
	private String department;
	private int salary;
	
	
	
	
	public EmployeeDTO() {
		
	}
	public EmployeeDTO(int employeeNo, String employeeName, String dateOfJoining, String department, int salary) {
		super();
		this.employeeNo = employeeNo;
		this.employeeName = employeeName;
		this.dateOfJoining = dateOfJoining;
		this.department = department;
		this.salary = salary;
	}
	
	@Id
//  @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "EmployeeNo", nullable = false,unique=true,length=10)
	public int getEmployeeNo() {
		return employeeNo;
	}
	public void setEmployeeNo(int employeeNo) {
		this.employeeNo = employeeNo;
	}
	
	@Column(name = "EmployeeName", nullable = false,length=100)
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	
	@Column(name = "DateofJoining", nullable = false,length=10)
	public String getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(String dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	
	@Column(name="Department", nullable=false, length=2)
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Column(name="Salary", nullable=false, length=10)
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	
	

}
