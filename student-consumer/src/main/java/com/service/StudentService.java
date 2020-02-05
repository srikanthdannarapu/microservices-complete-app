package com.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.model.Student;
import com.model.Students;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.proxy.StudentServiceProxy;

@Service
public class StudentService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private StudentServiceProxy proxy;
	
	@HystrixCommand(fallbackMethod = "getFallbackStudents", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "4"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "60000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "180000") }, threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "30"),
					@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "180000") })
	public Students getAllStudents() {
		ResponseEntity<Student[]> responseEntity = restTemplate.getForEntity("http://student-crud-service/api/students",
				Student[].class);
		Student[] studnets = responseEntity.getBody();
		Students stds = new Students();
		stds.setStudents(Arrays.asList(studnets));
		return stds;
	}
	
	@HystrixCommand(fallbackMethod = "getFallbackStudents", commandProperties = {
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "30000"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "4"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "60000"),
			@HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
			@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "180000") }, threadPoolProperties = {
					@HystrixProperty(name = "coreSize", value = "30"),
					@HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "180000") })
	public Students getStudents() {
		List<Student> students = proxy.getAllStudents();
		Students stds = new Students();
		stds.setStudents(students);
		return stds;
	}
	
	public Students getFallbackStudents() {
		List<Student> students = new ArrayList<Student>();
		students.add(new Student(0l, "dummyStundent", "XXXX"));
		Students stds = new Students();
		stds.setStudents(students);
		return stds;
	}

}
