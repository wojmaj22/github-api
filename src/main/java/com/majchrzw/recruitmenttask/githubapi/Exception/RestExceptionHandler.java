package com.majchrzw.recruitmenttask.githubapi.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@ControllerAdvice
@Component
public class RestExceptionHandler {

	@ResponseBody
	@ExceptionHandler( WebClientResponseException.class)
	public ResponseEntity<ExceptionBody> handleUserNotFoundException(WebClientResponseException exception){
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionBody(HttpStatus.NOT_FOUND.toString(), "User does not exist"));
	}
	
	@ResponseBody
	@ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
	public ResponseEntity<ExceptionBody> handleResponseStatusException(HttpMediaTypeNotAcceptableException exception){
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new ExceptionBody(HttpStatus.NOT_ACCEPTABLE.toString(), exception.getMessage()));
	}
	
}
