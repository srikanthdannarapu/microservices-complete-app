package com.springbootconfig.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.configmodel.DbSettings;

@RestController
public class GreetingController {
	
	@Value("${my.greeting}")
	private String greetingMessage;
	
	@Value("${app.description}")
	private String description;
	
	@Value("Static Message")
	private String staticMessage;
	
	@Value("${my.defaultGreeting: Default Greeting Good Morning}")
	private String defaultGreeting;
	
	@Value("${my.list.values}")
	private List<String> listValues;
	
	@Value("#{${db.connection}}")
	private Map<String, String> dbValues;
	
	@Autowired
	private DbSettings dbSettings;
	
	public GreetingController() {
		// TODO Auto-generated constructor stub
	}
	

	@GetMapping("/greeting")
	public String greeting() {
		return greetingMessage + " :" + description + ": " + staticMessage + " :" + defaultGreeting + " :" + listValues
				+ " :" + dbValues + " : " +dbSettings.getConnection() + dbSettings.getHost() ;

	}
}
