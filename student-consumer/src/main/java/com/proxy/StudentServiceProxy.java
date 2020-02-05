package com.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.model.Student;


//use http://localhost:8080/api without zuul
//use http://localhost:8899/api/students with zuul 
//8899 is the port where zuul is running
@FeignClient(url="http://localhost:8899/api",name="student-crud-service")
public interface StudentServiceProxy {
	
	@GetMapping("/students")
	public List<Student> getAllStudents();

}
