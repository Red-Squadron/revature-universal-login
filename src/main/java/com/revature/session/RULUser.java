package com.revature.session;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Data storage class for use in SessionManagement.
 * @author cavan
 *
 */
public class RULUser {

	public String firstname;
	public String lastname;
	public String emailaddress;
	public String middlename;
	public String authlevel;
	LocalDateTime creation;
	
	/**
	 * No args null base constructor which creates a bunch of empty string values for fields.
	 */
	public RULUser(){
		firstname = "";
		lastname = "";
		emailaddress = "";
		middlename = "";
		authlevel = "";
		creation = LocalDateTime.now();
	}
	
	/**
	 * Single string, adds email address.
	 * @param String email
	 */
	public RULUser(String email){
		this();
		this.emailaddress = email;
	}
		
	/**
	 * Returns true if it has been more than 2 hours since this session was created.
	 * @return boolean True if time expired, false otherwise.
	 */
	public boolean timeOut(){
		if(creation.plus(Duration.ofHours(2)).isBefore(LocalDateTime.now()))
			return true;
		return false;
	}

	/**
	 * Resets the creation time of the session.
	 */
	public void refresh(){
		creation = LocalDateTime.now();
	}
	
}