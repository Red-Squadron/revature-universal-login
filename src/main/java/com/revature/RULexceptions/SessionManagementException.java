package com.revature.RULexceptions;

public class SessionManagementException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8196585145260126859L;

	public SessionManagementException(){
		super("Take my children, just leave me alone!");
	}
	
	public SessionManagementException(String exMsg){
		super(exMsg);
	}
}
