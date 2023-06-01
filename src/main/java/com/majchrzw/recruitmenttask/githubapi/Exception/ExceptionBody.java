package com.majchrzw.recruitmenttask.githubapi.Exception;

public class ExceptionBody {
	
	private final String status;
	
	private final String Message;
	
	public ExceptionBody(String status, String message) {
		this.status = status;
		Message = message;
	}
	
	public String getStatus() {
		return status;
	}
	
	
	public String getMessage() {
		return Message;
	}
	
}
