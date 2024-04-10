package com.Mongodb.MongoDb.customexception;

import javax.management.RuntimeErrorException;

public class NoEmployeeFound  extends RuntimeException {
	
	
	private static final long serialVersionUID = 1L;
	
	 public NoEmployeeFound(String message) {
	        super(message);
	    }

}
