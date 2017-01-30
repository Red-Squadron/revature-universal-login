package SessionManagement;

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
	Duration timeOut;
	
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
		timeOut = Duration.ZERO;
	}
	
	/**
	 * Single string, adds email address.
	 * @param email
	 */
	public RULUser(String email){
		this();
		this.emailaddress = email;
	}
	
	/**
	 * Returns true if this User object is past it's timeout time, and thus should be removed from the SessionManagement map, false otherwise.
	 * @return boolean
	 */
	public boolean timeOut(){
		if(creation.plus(timeOut).isBefore(LocalDateTime.now()))
			return true;
		return false;
	}
	
	
	
}
