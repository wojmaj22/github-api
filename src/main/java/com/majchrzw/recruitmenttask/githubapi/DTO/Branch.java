package com.majchrzw.recruitmenttask.githubapi.DTO;

public class Branch {
	
	private String name;
	private Commit commit;
	
	public Branch() {
	}
	
	public Branch(String name, Commit commit) {
		this.name = name;
		this.commit = commit;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Commit getCommit() {
		return commit;
	}
	
	public void setSha(Commit commit) {
		this.commit = commit;
	}
}
