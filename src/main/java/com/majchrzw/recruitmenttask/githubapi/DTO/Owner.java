package com.majchrzw.recruitmenttask.githubapi.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Owner {
	
	@JsonProperty("login")
	private String login;
	
	public Owner(String login) {
		this.login = login;
	}
	
	public Owner() {
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String toSting(){
		return login;
	}
}
