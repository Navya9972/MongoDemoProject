package com.Mongodb.MongoDb.model;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "employee")
@Data
@NoArgsConstructor
public class Employee {
       
	
	@Id
       private int id;
	   @Field(name ="employee_name")
       private String empName;
       private String location;
       private BigDecimal salary;
       
       
}




