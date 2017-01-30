package com.revature.RULexceptions;

public class NoSuchSessionException extends SessionManagementException{
	public NoSuchSessionException(){
		super("AuthToken does not match an existing session.");
	}
}
