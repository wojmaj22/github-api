package com.majchrzw.recruitmenttask.githubapi.Service;

import com.majchrzw.recruitmenttask.githubapi.DTO.Branch;
import com.majchrzw.recruitmenttask.githubapi.DTO.Repository;
import com.majchrzw.recruitmenttask.githubapi.DTO.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiService {

	private WebClient client;
	
	@Autowired
	public ApiService( WebClient webClient){
		client = webClient;
	}
	
	
	public ArrayList<Repository> getListOfRepos(String username){
		List<Response> list = client.get()
				.uri("/users/{user}/repos", username)
				.retrieve()
				.bodyToFlux(Response.class)
				.filter(Response::isNotFork)
				.collectList()
				.block();
		ArrayList<Repository> repositories = new ArrayList<>();
		for ( Response response: list) {
			Repository tempRepo = new Repository(response.getOwner().getLogin(), response.getRepository());
			List<Branch> branchList = client.get()
					.uri("/repos/{user}/{repo}/branches", response.getOwner().toSting(), response.getRepository())
					.retrieve()
					.bodyToFlux(Branch.class)
					.collectList()
					.block();
			tempRepo.setBranches(new ArrayList<>(branchList));
			repositories.add(tempRepo);
		}
		
		return repositories;
	}
}
