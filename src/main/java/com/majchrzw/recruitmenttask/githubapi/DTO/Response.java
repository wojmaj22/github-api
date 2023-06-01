package com.majchrzw.recruitmenttask.githubapi.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Response {
	
	@JsonProperty("name")
	private String repository;
	
	private Owner owner;
	
	private boolean fork;
	
	private ArrayList<Branch> branches;
	
	public boolean isFork() {
		return fork;
	}
	
	public boolean isNotFork(){
		return !fork;
	}
	
	public void setFork(boolean fork) {
		this.fork = fork;
	}
	
	public Response() {
	}
	
	public Response(String repository, Owner owner, ArrayList<Branch> branches, boolean fork) {
		this.repository = repository;
		this.owner = owner;
		this.branches = branches;
		this.fork = fork;
	}
	
	public Response(String repository, Owner owner){
		this.repository = repository;
		this.owner = owner;
		branches = new ArrayList<>();
	}
	
	public String getRepository() {
		return repository;
	}
	
	public void setRepository(String repository) {
		this.repository = repository;
	}
	
	public Owner getOwner() {
		return owner;
	}
	
	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
	
	public ArrayList<Branch> getBranches() {
		return branches;
	}
	
	public void setBranches(ArrayList<Branch> branches) {
		this.branches = branches;
	}
	
}
