package com.exceptionhandler;

import java.sql.SQLException;

public class TableCheckerException extends SQLException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8139978080828934570L;

	public TableCheckerException(String msg) {
		super(msg);
	}
	
}
