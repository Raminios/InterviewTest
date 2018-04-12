package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class WriteFailException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
