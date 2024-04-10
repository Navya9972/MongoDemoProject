package com.Mongodb.MongoDb.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.Mongodb.MongoDb.ServiceInterface.EmployeeInterface;
import com.Mongodb.MongoDb.customexception.EmployeeIdNotFound;
import com.Mongodb.MongoDb.customexception.EmployeeNotFound;
import com.Mongodb.MongoDb.customexception.NoEmployeeFound;
import com.Mongodb.MongoDb.dto.EmployeeDto;
import com.Mongodb.MongoDb.model.LocationSummary;
import com.Mongodb.MongoDb.model.Employee;
import com.Mongodb.MongoDb.repository.EmployeeRepository;


@Service
public class EmployeeService implements EmployeeInterface {

	Logger logger = LoggerFactory.getLogger(EmployeeService.class);
   
   private   EmployeeRepository employeeRepo;

   
   //Constructor based injection
   public EmployeeService(EmployeeRepository employeeRepo)
   {
	   this.employeeRepo = employeeRepo;
	   
   }
   
   
  public EmployeeDto createEmployee(EmployeeDto empDto) {
	 try {
		 Employee emp = dtoToEntity(empDto);
		 Employee emp1 = employeeRepo.save(emp);
		 EmployeeDto savedDto = EntityToDto(emp1);
		 logger.info("Employee created!!!");
		 return savedDto;
		 
		 }catch(Exception e) {
			 
			 logger.error("Error while creating employee");
			 
			 throw e;
		 }
			    
}

   public List<Employee> getAllEmployee()
   {
	   List<Employee> empList = new ArrayList();
	    try {
	    	empList = employeeRepo.findAll();
    }catch(Exception e) {
    	
    }
	    logger.info("Employees List Fetched successfully");
	    
	    return  empList;
	    
   }
   
   
   public void deleteEmployee(Integer id)
   {
	   try {
		   employeeRepo.deleteById(id);
		   
	   }catch(Exception e) {
		   logger.error("Error while deleting the employee!!!");
		   
		   throw e;
	   }
	  
	   
   }
   
   public void deleteAllEmployees() {
	   try {
	         employeeRepo.deleteAll();
	   }catch (Exception e)
	   { 
		   logger.error("error while deleting the employee");
		   
		   throw e;
	   }
		}


   public EmployeeDto updateEmployee(EmployeeDto empDto,Integer id ) {
		 try {
			 Employee emp =  employeeRepo.findById(id).orElseThrow(() -> new EmployeeIdNotFound(id));
			 emp.setId(empDto.getId());
	    	 emp.setEmpName(empDto.getEmpName());
	    	 emp.setLocation(empDto.getLocation());
	    	 emp.setSalary(empDto.getSalary());
			 Employee updatedCar = employeeRepo.save(emp);
			 EmployeeDto updatedCarDto = EntityToDto(updatedCar);
			 logger.info("Employee updated successfully!!");
			 return updatedCarDto;
			 
			 }catch(Exception e) {
				 
				 logger.error("Error while updating the employee!!");
				 
				 throw e;
			 }
		
		 
	}
   
   
   public EmployeeDto getEmployeeById(Integer id) {
	    try {
	        Optional<Employee> optionalEmployee = employeeRepo.findById(id);
	        if (optionalEmployee.isPresent()) {
	            Employee employee = optionalEmployee.get();
	            EmployeeDto dto = EntityToDto(employee);
	            
	            logger.info("Employee fetched by id successfully");
	            
	            return dto;
	        } else {
	        	
	            logger.warn("Employee not found with id: " + id);
	            
	            throw new EmployeeIdNotFound(id);
	        }
	    } catch (Exception e) {
	        logger.error("Error while fetching the employee with id: " + id);
	        throw e;
	       
	    }
	}

   

   public boolean employeeExistsOrNot(Integer id) {
	    try {
	        return employeeRepo.existsById(id);
	    } catch (Exception e) {
	        throw new EmployeeIdNotFound(id);
	    }
	}

      
   
      public List<Employee> getEmployeesByLocation(String location) {
    	    List<Employee> employees = employeeRepo.findByLocation(location);
    	    if (employees.isEmpty()) {
    	        throw new NoEmployeeFound("Employee details not found with that location!");
    	    }
    	    return employees;
    	}

     
      public List<Employee> getEmployeesBySalaryRange(BigDecimal minSalary, BigDecimal maxSalary) {
    	    List<Employee> employees = employeeRepo.findBySalaryBetween(minSalary, maxSalary);
    	    if (employees.isEmpty()) {
    	        throw new NoEmployeeFound("Employee deatils not found between this salary");
    	    }
    	    return employees;
    	}

     
      
      public long countEmployees() {
    	    return employeeRepo.count();
    	}

     
     public EmployeeDto updateEmployeeSalary(Integer id, BigDecimal newSalary) {
    	    Optional<Employee> optionalEmployee = employeeRepo.findById(id);
    	    if (optionalEmployee.isPresent()) {
    	        Employee employee = optionalEmployee.get();
    	        employee.setSalary(newSalary);
    	        EmployeeDto dto =  EntityToDto(employee);
    	        logger.info("employee salary updates successfully!!");
	            return dto;
    	    } else {
    	    	logger.warn("Employee id not found!");
    	    	 throw new EmployeeIdNotFound(id);
    	    }
    	}
     
     public List<Employee> findEmployeesByName(String regex) {
    	    List<Employee> employees = employeeRepo.findByEmpNameRegexIgnoreCase(regex);
    	    if (employees.isEmpty()) {
    	    	throw new EmployeeNotFound(regex);
    	    }
    	    return employees;
    	}

     
     public List<Employee> findByNameOrLocation(String name, String location) {
    	    List<Employee> employees = employeeRepo.findByEmpNameOrLocation(name, location);
    	    if (employees.isEmpty()) {
    	        throw new EmployeeNotFound(name,location);
    	    }
    	    return employees;
    	}
     
     public List<Employee> findBySalary(BigDecimal salary) {
    	    List<Employee> employees = employeeRepo.findBySalary(salary);
    	    if (employees.isEmpty()) {
    	        throw new EmployeeNotFound(salary);
    	    }
    	    return employees;
    	}

     
    
     
     
     public List<Employee> firstOrderBySalaryAsc() {
         return employeeRepo.findFirstByOrderBySalaryAsc();
     }
     
     
     
     public List<Employee> findTop2ByOrderBySalaryAsc() {
         try {
             return employeeRepo.findTop2ByOrderBySalaryAsc();
         } catch (Exception ex) {
            throw ex;
         }
     }
 
     
     public List<Employee> findByNameEndingWith(String suffix) {
         try {
             List<Employee> employees = employeeRepo.findByempNameEndingWith(suffix);
             if (employees.isEmpty()) {
                 throw new  EmployeeNotFound(suffix);
             }
             return employees;
         } catch (EmployeeNotFound e) {
             throw new  EmployeeNotFound(suffix);
         }
     }
 
     
     public List<Employee> findByEmployeeNa(String name) {
         try {
             List<Employee> employees = employeeRepo.findByTheEmployeeFirstname(name);
             if (employees.isEmpty()) {
                 throw new EmployeeNotFound(name);
             }
             return employees;
         } catch (Exception e) {
             throw new EmployeeNotFound(name);
         }
     }
     
     
     public List<Employee> findEmployeesWithHighestSalaryByLocation() {
         return employeeRepo.findEmployeesWithHighestSalaryByLocation();
     }
     
     
     
     public List<Employee> findEmployeesWithLowestSalary() {
    	 try {
         return employeeRepo.findEmployeesWithLowestSalary();
    	 }catch(Exception e) {
    		 throw new RuntimeException("Error occurred while fetching employees by name ", e);
     }
 }

     
     public List<LocationSummary> findAverageSalaryByLocation(String location) {
         return employeeRepo.countLocationAndSumSalary(location);
     }
    
     
     public List<Employee> findEmployeesWithSalaryGreaterThan(BigDecimal thresold) {
         try {
             List<Employee> employees = employeeRepo.findEmployeesWithSalaryGreaterThan(thresold);
             if (employees.isEmpty()) {
                 throw new  EmployeeNotFound(thresold);
             }
             return employees;
         } catch (EmployeeNotFound e) {
             throw new  EmployeeNotFound(thresold);
         }
     }
 
     
     public List<Employee> findEmployeesWithSalaryLowerThan(BigDecimal lower) {
         try {
             List<Employee> employees = employeeRepo.findEmployeesWithSalaryLowerThanEqual(lower);
             if (employees.isEmpty()) {
                 throw new EmployeeNotFound(lower);
             }
             return employees;
         } catch (EmployeeNotFound  e) {
        	 throw new  EmployeeNotFound(lower);
         }
     }
 
      
     public long countEmployeesInLocation(String location) {
         return employeeRepo.countByLocation(location);

 	}
     
     public List<LocationSummary> getEmployeeCountAndAverageSalaryByLocationWithMinSalary(String location, BigDecimal minSalary) {
         return employeeRepo.getEmployeeCountAndAverageSalaryByLocationMinSalary(location, minSalary);
     }
 

 
     
     private Employee dtoToEntity(EmployeeDto dto) {
    	 Employee emp = new Employee();
    	 emp.setId(dto.getId());
    	 emp.setEmpName(dto.getEmpName());
    	 emp.setLocation(dto.getLocation());
    	 emp.setSalary(dto.getSalary());
    	 return emp;
     }

      private EmployeeDto EntityToDto(Employee emp)
      {
    	   EmployeeDto dto = new EmployeeDto();
    	   dto.setId(emp.getId());
    	   dto.setEmpName(emp.getEmpName());
    	   dto.setLocation(emp.getLocation());
    	   dto.setSalary(emp.getSalary());
    	   return dto;
      }


	


	



	



}


   
   



