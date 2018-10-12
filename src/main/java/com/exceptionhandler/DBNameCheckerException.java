package com.exceptionhandler;

import com.mongodb.MongoClientException;

public class DBNameCheckerException extends MongoClientException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7028404405071207934L;

	public DBNameCheckerException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

}
