package com.revature.session;

import java.util.HashMap;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.exceptions.NoSuchSessionException;
import com.revature.exceptions.SessionManagementException;
import com.revature.exceptions.SessionTimeOutException;

/**
 * Class containing session management related methods for use by servlets.
 * @author cavan
 */
public class SessionManagement {

	private HashMap<String, RULUser> authmap;
	
	private static SessionManagement manager;
	
	private SessionManagement(){
		authmap = new HashMap<>();
	}
	
	/**
	 * Returns singleton instance of SessionManagement.
	 * @return SessionManagement SessionManagement singleton.
	 */
	public static SessionManagement getSessionManager(){
		if(manager==null)
			manager = new SessionManagement();
		return manager;
	}
	
	/**
	 * Method for creating an active session. Returns the authtoken associated with that session.
	 * @param RULUser User being logged in
	 * @return String User authorization token
	 */
	public String createSession(RULUser usr){
		String authtoken;
		authtoken = BCrypt.hashpw(usr.getEmailaddress()+usr.getAuthlevel()+usr.creation, BCrypt.gensalt());
		authmap.put(authtoken, usr);
		return authtoken;
	}
	
	/**
	 * Return the user's session, throws an exception if there's a problem with the user's session.
	 * @param String User authorization token
	 * @return RULUser User associated with authorization token
	 */
	public RULUser getSession(String authtoken) throws SessionManagementException{
		if((authmap.containsKey(authtoken))&&(authmap.get(authtoken).timeOut()))
		{
			authmap.remove(authtoken);
			throw new SessionTimeOutException();
		}
		if(!authmap.containsKey(authtoken))
			throw new NoSuchSessionException();
		return authmap.get(authtoken);
	}
	
	/**
	 * Iterates through the authmap and removes timed out sessions.
	 */
	public void cleanUp(){
		// Thread-safe lambda map iteration =D
		authmap.forEach((authtoken,usr)->{
			if(usr.timeOut())
				authmap.remove(authtoken);
		});
	}
}
