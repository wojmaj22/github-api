package com.majchrzw.recruitmenttask.githubapi.Controller;

import com.majchrzw.recruitmenttask.githubapi.DTO.Repository;
import com.majchrzw.recruitmenttask.githubapi.DTO.Response;
import com.majchrzw.recruitmenttask.githubapi.Exception.ExceptionBody;
import com.majchrzw.recruitmenttask.githubapi.Service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Objects;

// TODO - naprawić 406 error
@RestController
public class MainController {
	
	ApiService service;
	
	
	@Autowired
	public MainController( ApiService apiService){
		service = apiService;
	}

	@GetMapping(path = "/api/{user}")
	public ArrayList<Repository> getRepos (@PathVariable(name = "user") String user, @RequestHeader(name = "Accept") String accept) throws HttpMediaTypeNotAcceptableException{
		if(Objects.equals(accept, "application/xml")){
			throw new HttpMediaTypeNotAcceptableException("XML not supported");
		}
		return service.getListOfRepos(user);

	}
	
	
}
