package com.revature.exceptions;

public class SessionTimeOutException extends SessionManagementException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1731427002795591544L;

	public SessionTimeOutException(){
		super("Session has timed out.");
	}
}
