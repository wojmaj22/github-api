package com.majchrzw.recruitmenttask.githubapi.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.majchrzw.recruitmenttask.githubapi.Exception.ExceptionBody;
import com.majchrzw.recruitmenttask.githubapi.Service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class MainController {
	
	ApiService service;
	
	
	@Autowired
	public MainController( ApiService apiService){
		service = apiService;
	}

	@GetMapping(path = "/api/{user}")
	public ResponseEntity<String> getRepos (@PathVariable(name = "user") String user, @RequestHeader(name = "Accept") String accept) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		if(Objects.equals(accept, "application/xml")) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(objectMapper.writeValueAsString(new ExceptionBody(HttpStatus.NOT_ACCEPTABLE.toString(), "XML not supported")));
		}
		return ResponseEntity.ok(objectMapper.writeValueAsString(service.getListOfRepos(user)));

	}
	
	
}
