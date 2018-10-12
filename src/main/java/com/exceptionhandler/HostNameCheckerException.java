package com.exceptionhandler;

import com.mongodb.MongoClientException;
import com.mongodb.MongoSocketException;
import com.mongodb.ServerAddress;

public class HostNameCheckerException extends MongoSocketException {

	public HostNameCheckerException(String message, ServerAddress serverAddress) {
		super(message, serverAddress);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8070654489995981999L;

	
}
