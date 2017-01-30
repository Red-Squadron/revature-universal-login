package com.revature.RULexceptions;

public class SessionTimeOutException extends SessionManagementException{
	public SessionTimeOutException(){
		super("Session has timed out.");
	}
}
