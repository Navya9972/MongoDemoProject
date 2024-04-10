package com.Mongodb.MongoDb.customexception;

public class EmployeeIdNotFound extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeIdNotFound(int id)
	{
		super(String.format("Employee not found with Id: %d" , id));
	}
	
}
