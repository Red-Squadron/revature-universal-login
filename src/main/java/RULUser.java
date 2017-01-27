
/**
 * Data storage class for use in SessionManagement.
 * @author cavan
 *
 */
public class RULUser {

	String firstname;
	String lastname;
	String emailaddress;
	String middlename;
	String authlevel;
	
	/**
	 * No args null base constructor which creates a bunch of empty string values for fields.
	 */
	public RULUser(){
		firstname = "";
		lastname = "";
		emailaddress = "";
		middlename = "";
		authlevel = "";
	}
	
	/**
	 * Single string, adds email address.
	 * @param email
	 */
	public RULUser(String email){
		this();
		this.emailaddress = email;
	}
	
	
	
	
}
