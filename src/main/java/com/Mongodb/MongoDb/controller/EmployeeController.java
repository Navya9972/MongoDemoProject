package com.Mongodb.MongoDb.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Mongodb.MongoDb.Response.ResponseMessage;
import com.Mongodb.MongoDb.customexception.EmployeeIdNotFound;
import com.Mongodb.MongoDb.dto.EmployeeDto;
import com.Mongodb.MongoDb.model.LocationSummary;
import com.Mongodb.MongoDb.model.Employee;
import com.Mongodb.MongoDb.payload.ApiResponse;
import com.Mongodb.MongoDb.service.EmployeeService;

@RestController
@RequestMapping("/api/emp")
public class EmployeeController {
   
	@Autowired
	private EmployeeService empService;
	
	
	 //method 1
	  @PostMapping("/createEmployee")
	  public ResponseEntity<ApiResponse>CreateStudent(@RequestBody EmployeeDto emp)
	  {
		    EmployeeDto employee=empService.createEmployee(emp);
			ApiResponse response = new ApiResponse(true, ResponseMessage.Create_Message, employee );
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	  }

	  //method 2
	  @GetMapping("/getAllEmployee")
		public ResponseEntity<ApiResponse> getAllUsers() {
			List<Employee> details = empService.getAllEmployee();
			ApiResponse response = new ApiResponse(true, ResponseMessage.Fetch_Message, details);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	  
	  //method 3
	  @DeleteMapping("/DeleteEmployee/{id}")
	  public ResponseEntity<ApiResponse> deleteEmployee(@PathVariable Integer id) {
	      empService.deleteEmployee(id);
	      ApiResponse response = new ApiResponse(true, ResponseMessage.Delete_message, true);
	      return new ResponseEntity<>(response, HttpStatus.OK);
	  }

	  //method 4
	  @DeleteMapping("/DeleteAllEmployee")
	  public ResponseEntity<ApiResponse>deleteAllEmployee()
	  {
		    empService.deleteAllEmployees();
			ApiResponse response = new ApiResponse(true, ResponseMessage.Delete_message, true );
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	  }
	  
	  //method 5
	  @PutMapping("/updateEmployee/{id}")
	  public ResponseEntity<ApiResponse>updateEmployee(@RequestBody EmployeeDto emp,@PathVariable Integer id)
	  {
		    EmployeeDto employee= empService.updateEmployee(emp, id);
			ApiResponse response = new ApiResponse(true, ResponseMessage.Update_Message, employee );
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	  }
	  
	  //method 6
	  @GetMapping("/getEmployee/{id}")
		public ResponseEntity<ApiResponse> getUser(@PathVariable("id") Integer id) {
			EmployeeDto emp2 = empService.getEmployeeById(id);
			ApiResponse response = new ApiResponse(true, ResponseMessage.Fetch_Message, emp2);
			return new ResponseEntity<>(response, HttpStatus.OK);
			
	}
	  //method 7
	  @GetMapping("/EmployeeExits/{id}")
	  public ResponseEntity<ApiResponse> getEmployeeExitsOrNot(@PathVariable("id") Integer id) {
	      boolean empExists = empService.employeeExistsOrNot(id);
	      if(!empExists)
	      {
	    	  throw new EmployeeIdNotFound(id);
	      }
	      ApiResponse response = new ApiResponse(false, ResponseMessage.Fetch_Message, empExists);
	      return new ResponseEntity<>(response, HttpStatus.OK);
	  }
	  
	  //method 8
	  @PutMapping("/updatesalary/{id}/{salary}")
	  public ResponseEntity<ApiResponse> updateEmployeeSalary(@PathVariable Integer id, @PathVariable BigDecimal salary) {
	      EmployeeDto employee = empService.updateEmployeeSalary(id, salary);
	      ApiResponse response = new ApiResponse(true, ResponseMessage.Update_Message, employee);
	      return new ResponseEntity<>(response, HttpStatus.OK);
	  }
	  
	  //method 9
	  @GetMapping("/employees/count")
	   public ResponseEntity<Long> countEmployees() {
	        long employeeCount = empService.countEmployees();
	        return new ResponseEntity<>(employeeCount, HttpStatus.OK);
	    }
	  
	  //method 10
	  @GetMapping("/getEmployess/{location}")
	   public ResponseEntity<ApiResponse> getEmployeeLocation(@PathVariable String location) {
		  List<Employee> emp2 = empService.getEmployeesByLocation(location);
			ApiResponse response = new ApiResponse(true, ResponseMessage.Fetch_Message, emp2);
			return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	  
	  //method 11
	  @GetMapping("/getEmployessBySalary/{minsalary}/{maxsalary}")
	   public ResponseEntity<ApiResponse> getEmployeeBySalary (@PathVariable ("minsalary") BigDecimal minsalary ,@PathVariable ("maxsalary")BigDecimal maxsalary){
		  List<Employee> emp2 = empService.getEmployeesBySalaryRange(minsalary, maxsalary);
			ApiResponse response = new ApiResponse(true, ResponseMessage.Fetch_Message, emp2);
			return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	  
	  //method 12
	  @GetMapping("/getEmployes/{regex}")
	   public ResponseEntity<ApiResponse> getEmployeeName(@PathVariable String regex) {
		  List<Employee> emp2 = empService.findEmployeesByName(regex);
			ApiResponse response = new ApiResponse(true, ResponseMessage.Fetch_Message, emp2);
			return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	  
	  //method 13
	  @GetMapping("/getemployees/{name}/{location}")
	    public ResponseEntity<ApiResponse> getEmployeesNameOrLocation(@PathVariable String name ,@PathVariable String location)
	         {
		    List<Employee> emp2 = empService.findByNameOrLocation(name, location);
			ApiResponse response = new ApiResponse(true, ResponseMessage.Fetch_Message, emp2);
			return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	  
	  //method 14
	  @GetMapping("/getemployees/salary/{salary}")
	    public ResponseEntity<ApiResponse> getEmployeesBySalary(@PathVariable BigDecimal salary) {
	       
		  List<Employee> emp2 = empService.findBySalary(salary);
			ApiResponse response = new ApiResponse(true, ResponseMessage.Fetch_Message, emp2);
			return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	  

	  
	    //method 15
	    @GetMapping("/employee/first-by-salary")
	    public ResponseEntity<ApiResponse> getEmployeesBySalaryAscending() {
	       
		  List<Employee> emp2 = empService.firstOrderBySalaryAsc();
			ApiResponse response = new ApiResponse(true, ResponseMessage.Fetch_Message, emp2);
			return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	    
        //method 16
	    @GetMapping("/employee/Top-by-salary")
	    public ResponseEntity<ApiResponse> getEmployeesTopBySalaryAscending() {
	       
		  List<Employee> emp2 = empService.findTop2ByOrderBySalaryAsc();
			ApiResponse response = new ApiResponse(true, ResponseMessage.Fetch_Message, emp2);
			return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	    
	    
	    //method 17
	    @GetMapping("/employees/ending-withName/{suffix}")
	    public ResponseEntity<ApiResponse> getEmployeesByNameEndingWith(@PathVariable String suffix) {
	    	List<Employee> emp2 = empService.findByNameEndingWith(suffix);
			ApiResponse response = new ApiResponse(true, ResponseMessage.Fetch_Message, emp2);
			return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	    
	    //method 18
	    @GetMapping("/employees/naameQuery/{name}")
	    public ResponseEntity<ApiResponse> getEmployeesByNameQuery(@PathVariable String name) {
	    	List<Employee> emp2 = empService.findByEmployeeNa(name);
			ApiResponse response = new ApiResponse(true, ResponseMessage.Fetch_Message, emp2);
			return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	    
	    //method 19
	    @GetMapping("/employees/highest-salary-by-location")
	    public ResponseEntity<ApiResponse>  findEmployeesWithHighestSalaryByLocation() {
	    	List<Employee> emp2 = empService.findEmployeesWithHighestSalaryByLocation();
			ApiResponse response = new ApiResponse(true, ResponseMessage.Fetch_Message, emp2);
			return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	   
	    //method 20
	    @GetMapping("/employee/lowest-salary")
	    public ResponseEntity<ApiResponse> findEmployeesWithLowestSalary() {
	        List<Employee> emp2 = empService.findEmployeesWithLowestSalary();
			ApiResponse response = new ApiResponse(true, ResponseMessage.Fetch_Message, emp2);
			return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	    
	    //method 21
	    @GetMapping("/average-salary/{location}")
	    public ResponseEntity<ApiResponse> calculateAvgSalaryByLocation(@PathVariable String location) {
	    	List<LocationSummary> emp2 = empService.findAverageSalaryByLocation(location);
			ApiResponse response = new ApiResponse(true, ResponseMessage.Fetch_Message, emp2);
			return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	    
	    
	    //method 22
	    @GetMapping("/salary-greater-than/{threshold}")
	    public ResponseEntity<ApiResponse> findEmployeesWithSalaryGreaterThan(@PathVariable BigDecimal threshold) {
		    	List<Employee> emp2 = empService.findEmployeesWithSalaryGreaterThan(threshold);
				ApiResponse response = new ApiResponse(true, ResponseMessage.Fetch_Message, emp2);
				return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	    
	    //method 22
	    @GetMapping("/salary-lower-than/{lower}")
	    public ResponseEntity<ApiResponse> findEmployeesWithSalaryLowerThan(@PathVariable BigDecimal lower) {
		    	List<Employee> emp2 = empService.findEmployeesWithSalaryLowerThan(lower);
				ApiResponse response = new ApiResponse(true, ResponseMessage.Fetch_Message, emp2);
				return new ResponseEntity<>(response, HttpStatus.OK);
	    }
	    
	    @GetMapping("/count/{location}")
	    public ResponseEntity<ApiResponse> gtCountOfEmployeesInLocation(@PathVariable String location) {
	    	long emp2 = empService.countEmployeesInLocation(location);
			ApiResponse response = new ApiResponse(true, ResponseMessage.Fetch_Message, emp2);
			return new ResponseEntity<>(response, HttpStatus.OK);  
	    }
	    
	    
	    @GetMapping("/employees/location")
	    public List<LocationSummary> getEmployeeCountAndAverageSalaryByLocationWithMinSalary(
	            @RequestParam String location,
	            @RequestParam BigDecimal minSalary) {
	        return empService.getEmployeeCountAndAverageSalaryByLocationWithMinSalary(location, minSalary);
	    }
	}
	
	   
	    
	  


	       
	  


			  
	  
  
