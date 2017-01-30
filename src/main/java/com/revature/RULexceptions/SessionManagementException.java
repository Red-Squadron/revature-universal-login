package com.revature.RULexceptions;

public class SessionManagementException extends Exception{
	public SessionManagementException(){
		super("Take my children, just leave me alone!");
	}
	
	public SessionManagementException(String exMsg){
		super(exMsg);
	}
}
