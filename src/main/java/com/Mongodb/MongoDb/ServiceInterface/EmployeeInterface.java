package com.Mongodb.MongoDb.ServiceInterface;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.Mongodb.MongoDb.dto.EmployeeDto;
import com.Mongodb.MongoDb.model.Employee;
import com.Mongodb.MongoDb.model.LocationSummary;

public interface EmployeeInterface {
	
	
	public 	EmployeeDto createEmployee(EmployeeDto empDto);
	public List<Employee> getAllEmployee();
	public void deleteEmployee(Integer id);
	public void deleteAllEmployees();
	public EmployeeDto updateEmployee(EmployeeDto empDto,Integer id);
	public EmployeeDto getEmployeeById(Integer id);
	public boolean employeeExistsOrNot(Integer id) ;
    public EmployeeDto updateEmployeeSalary(Integer id, BigDecimal newSalary);
    public long countEmployees();
    public List<Employee> getEmployeesByLocation(String location);
    public List<Employee> getEmployeesBySalaryRange(BigDecimal minSalary, BigDecimal maxSalary);
    public List<Employee> findEmployeesByName(String regex);
    
    public List<Employee> findByNameOrLocation(String name, String location);
    public List<Employee> findBySalary(BigDecimal salary);
    
    public List<Employee> firstOrderBySalaryAsc() ;
    public List<Employee> findTop2ByOrderBySalaryAsc();
    
    public List<Employee> findByNameEndingWith(String suffix);
    
    public List<Employee> findByEmployeeNa(String name) ;
    
    public List<Employee> findEmployeesWithHighestSalaryByLocation();
    public List<Employee> findEmployeesWithLowestSalary() ;
    
    public List<Employee> findEmployeesWithSalaryGreaterThan(BigDecimal threshold) ;
    
    public List<LocationSummary > findAverageSalaryByLocation(String location);
    public long countEmployeesInLocation(String location);
    
    public List<LocationSummary> getEmployeeCountAndAverageSalaryByLocationWithMinSalary(String location, BigDecimal minSalary);
    
	  
		     
	 
} 
