package com.revature.session;

import java.util.HashMap;

import com.revature.RULexceptions.NoSuchSessionException;
import com.revature.RULexceptions.SessionManagementException;
import com.revature.RULexceptions.SessionTimeOutException;


/**
 * Class containing session management related methods for use by servlets.
 * @author cavan
 */
public class SessionManagement {

	private HashMap<String, RULUser> authmap;
	
	private static SessionManagement manager;
	
	private SessionManagement(){
		authmap = new HashMap<String, RULUser>();
	}
	
	/**
	 * Returns singleton instance of SessionManagement.
	 * @return the SessionManagement object.
	 */
	public static SessionManagement getSessionManager(){
		if(manager==null)
			manager = new SessionManagement();
		return manager;
	}
	
	/**
	 * TODO: Decide on and implement the method for generating authorization tokens.
	 * Method for creating an active session. Returns the authtoken associated with that session.
	 * @param usr
	 * @return
	 */
	public String createSession(RULUser usr){
		String authtoken;
		authtoken = usr.authlevel+usr.emailaddress+usr.firstname+usr.lastname+usr.timeOut+usr.creation; //This will obviously need to be changed.
		//TODO: Insert decided upon implementation here.
		authmap.put(authtoken, usr);
		return authtoken;
	}
	
	/**
	 * Return the user's session, throws an exception if there's a problem with the user's session.
	 * @param authtoken
	 * @return
	 */
	public RULUser getSession(String authtoken) throws SessionManagementException{
		if(authmap.containsKey(authtoken))
			if(authmap.get(authtoken).timeOut()){
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
		System.gc();
	}
}
