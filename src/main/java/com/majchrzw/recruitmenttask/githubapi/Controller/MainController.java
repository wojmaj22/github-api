package com.majchrzw.recruitmenttask.githubapi.Controller;

import com.majchrzw.recruitmenttask.githubapi.DTO.Repository;
import com.majchrzw.recruitmenttask.githubapi.Service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class MainController {
	
	ApiService service;
	
	
	@Autowired
	public MainController( ApiService apiService){
		service = apiService;
	}

	@GetMapping(path = "/api/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<Repository> getRepos (@PathVariable(name = "user") String user) {
		
		return service.getListOfRepos(user);
	}
	
	
}
