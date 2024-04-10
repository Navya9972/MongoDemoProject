package com.Mongodb.MongoDb.model;

import java.math.BigDecimal;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class LocationSummary {


	    private String location;
	    private BigDecimal averageSalary;
	    private BigDecimal totalSalary;
        private int count;
	}

