package com.Mongodb.MongoDb.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.Mongodb.MongoDb.model.LocationSummary;
import com.Mongodb.MongoDb.model.Employee;


@Repository
public interface EmployeeRepository extends MongoRepository<Employee,Integer>{
	
	
	List<Employee> findByLocation(String location);
	

	 List<Employee> findFirstByOrderBySalaryAsc();
	 
	 List<Employee> findTop2ByOrderBySalaryAsc();
	 
	 List<Employee> findByempNameEndingWith(String suffix);
	 
	 List<Employee> findBylocationNotLike(String location);
	 
	 
	@Query("{'empName': {'$regex': '^?0', '$options': 'i'}}")
	List<Employee> findByEmpNameRegexIgnoreCase(String name);

	
	@Query("{'salary' : {'$gte' : ?0, '$lte' : ?1}}")
    List<Employee> findBySalaryBetween(BigDecimal minSalary, BigDecimal maxSalary);
	
	
	@Query("{'$or': [{'empName': ?0}, {'location': ?1}]}")
	List<Employee> findByEmpNameOrLocation(String Name, String Location);

	@Query(value = "{'salary':?0}",fields = "{'empName':1}") //included
	//@Query(value = "{'age':?0}",fields = "{'domain':0}")   //excluded
	List<Employee>findBySalary(BigDecimal Salary);
	

	 
	 @Query(value="{ 'empName' : ?0 }", fields="{ 'empName' : 1, 'location' : 1}")
	  List<Employee> findByTheEmployeeFirstname(String name);
	 
	 
	 
	 
	 @Aggregation(pipeline = {
			    "{$sort: { location: 1, salary: -1 } }",
			    "{$group: { _id: '$location', highestSalaryEmployee: { $first: '$$ROOT' } } }",
			    "{$project: { _id: 0, location: '$_id', highestSalaryEmployee: 1 } }"
			})
			List<Employee> findEmployeesWithHighestSalaryByLocation();

	 
	 
	 
	 @Aggregation(pipeline = {
		        "{ $sort: { salary: 1 } }",
		        "{ $limit: 1 }"
		    })
	 List<Employee> findEmployeesWithLowestSalary();
	 
	 
		
	 @Aggregation(pipeline = {
			    "{$match: {salary: {$gt: ?0}}}"
			})
	List<Employee> findEmployeesWithSalaryGreaterThan(BigDecimal threshold);
	 
	 
	 @Aggregation(pipeline = {
			    "{$match: {salary: {$lte: ?0}}}"
			})
	List<Employee> findEmployeesWithSalaryLowerThanEqual(BigDecimal Lower);

	 
	long countByLocation(String location);
	
	

	@Aggregation({
	    "{$match: {location: ?0}}",
	    "{$group: {_id: '$location', averageSalary: {$avg: '$salary'}}}",
	    "{$project: {location: '$_id', averageSalary: 1}}"
	})
	List<LocationSummary> countLocationAndSumSalary(String location);
	
	
	 @Aggregation({
	       "{$match: {location: ?0, salary: {$gt: ?1}}}",
	       "{$group: {_id: '$location', count: {$sum: 1}, totalSalary: {$sum: '$salary'}}}",
	       "{$project: {location: '$_id', count: 1, totalSalary: 1, averageSalary: {$divide: ['$totalSalary', '$count']}}}"
	    })
	List<LocationSummary> getEmployeeCountAndAverageSalaryByLocationMinSalary(String location, BigDecimal minSalary);
	 

}


