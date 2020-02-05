package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.Students;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.service.StudentService;

@RestController
@RequestMapping("/student-consumer")
public class StudentConsumerController {

	@Autowired
	private StudentService studentService;
	
	//@HystrixCommand ..enable this only if hystrix dashboard doesn't showup
	@GetMapping("/studentsList")
	public ResponseEntity<Students> getAllStudents() {
		Students students = studentService.getAllStudents();
		return ResponseEntity.ok(students);
	}
	
	//@HystrixCommand ..enable this only if hystrix dashboard doesn't showup
	@GetMapping("/allStudents")
	public ResponseEntity<Students> getStudents() {
		Students students = studentService.getStudents();
		return ResponseEntity.ok(students);
	}
}
