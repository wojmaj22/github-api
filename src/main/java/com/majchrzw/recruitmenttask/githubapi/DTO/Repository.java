package com.majchrzw.recruitmenttask.githubapi.DTO;

import java.util.ArrayList;

public class Repository {
	
	private String login;
	private String name;
	private ArrayList<Branch> branches;
	
	public Repository(String login, String name, ArrayList<Branch> branches) {
		this.login = login;
		this.name = name;
		this.branches = branches;
	}
	
	public Repository(String login, String name) {
		this.login = login;
		this.name = name;
		branches = new ArrayList<>();
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public ArrayList<Branch> getBranches() {
		return branches;
	}
	
	public void setBranches(ArrayList<Branch> branches) {
		this.branches = branches;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Repository that = (Repository) o;
		
		if (!login.equals(that.login)) return false;
		if (!name.equals(that.name)) return false;
		return branches.equals(that.branches);
	}
	
	@Override
	public int hashCode() {
		int result = login.hashCode();
		result = 31 * result + name.hashCode();
		result = 31 * result + branches.hashCode();
		return result;
	}
}
