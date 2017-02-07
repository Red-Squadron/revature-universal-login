package com.revature.RULexceptions;

public class NoSuchSessionException extends SessionManagementException{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5020470439755656376L;

	public NoSuchSessionException(){
		super("AuthToken does not match an existing session.");
	}
}
