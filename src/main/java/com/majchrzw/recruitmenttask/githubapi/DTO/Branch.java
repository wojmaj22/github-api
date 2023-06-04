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
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Branch branch = (Branch) o;
		
		if (!name.equals(branch.name)) return false;
		return commit.equals(branch.commit);
	}
	
	@Override
	public int hashCode() {
		int result = name.hashCode();
		result = 31 * result + commit.hashCode();
		return result;
	}
}
