package com.majchrzw.recruitmenttask.githubapi.DTO;

import java.util.Objects;

public class Commit {
	
	private String sha;
	
	public Commit(){}
	
	public Commit( String sha){
		this.sha = sha;
	}
	
	public String getSha() {
		return sha;
	}
	
	public void setSha(String sha) {
		this.sha = sha;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Commit commit = (Commit) o;
		
		return Objects.equals(sha, commit.sha);
	}
	
	@Override
	public int hashCode() {
		return sha != null ? sha.hashCode() : 0;
	}
}
