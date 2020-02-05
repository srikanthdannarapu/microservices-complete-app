package com.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.model.Student;
import com.model.Students;

@RestController
@RequestMapping("/student-consumer")
public class StudentConsumerController {

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/studentsList")
	public ResponseEntity<Students> getAllStudents() {
		ResponseEntity<Student[]> responseEntity = restTemplate.getForEntity("http://student-crud-service/api/students",
				Student[].class);
		Student[] studnets = responseEntity.getBody();
		Students stds = new Students();
		stds.setStudents(Arrays.asList(studnets));
		return ResponseEntity.ok(stds);
	}
}
