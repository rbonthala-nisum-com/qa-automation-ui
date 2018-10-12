package com.exceptionhandler;

import com.mongodb.MongoException;

public class MongoDBConnectionException extends MongoException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1085808808687253027L;
	
	public MongoDBConnectionException(int code, String msg, Throwable t) {
		super(code, msg, t);
		// TODO Auto-generated constructor stub
	}
	public MongoDBConnectionException(int code, String msg) {
		super(code, msg);
		// TODO Auto-generated constructor stub
	}
	public MongoDBConnectionException(String msg, Throwable t) {
		super(msg, t);
		// TODO Auto-generated constructor stub
	}
	public MongoDBConnectionException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
