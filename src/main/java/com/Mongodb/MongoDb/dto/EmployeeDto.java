package com.Mongodb.MongoDb.dto;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeDto {

	@Id
   private int id;
	@Field(name ="employee_name")
	   @NotNull
       private String empName;
	   @NotNull
       private String location;
	
       private BigDecimal salary;
 
       
}

