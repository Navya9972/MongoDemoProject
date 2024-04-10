package com.Mongodb.MongoDb.customexception;

import java.math.BigDecimal;

public class EmployeeNotFound extends RuntimeException {
	private static final long serialVersionUID = 1L;
	

	public EmployeeNotFound(String name)
	{
		super(String.format("Employee not found with name:%s " , name));
	}
	
	 public EmployeeNotFound(String name, String location) {
	        super(String.format("Employee not found with name: %s and location: %s", name, location));
	    }
	 
	 public EmployeeNotFound(BigDecimal salary)
		{
			super(String.format("Employee not found with salary:%s " , salary));
		}
	 
	 public EmployeeNotFound(BigDecimal maxsalary,BigDecimal minsalary,String name)
		{
			super(String.format("Employee not found with salary:%s and name :%s " ,maxsalary,minsalary,name));
		}
	 
	 
		
	}

	

