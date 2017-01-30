package com.revature.session;

import java.util.HashMap;

import org.mindrot.jbcrypt.BCrypt;
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
	 * Method for creating an active session. Returns the authtoken associated with that session.
	 * @param usr
	 * @return
	 */
	public String createSession(RULUser usr){
		String authtoken;
		authtoken = BCrypt.hashpw(usr.emailaddress+usr.authlevel+usr.creation, BCrypt.gensalt());
		authmap.put(authtoken, usr);
		return authtoken;
	}
	
	/**
	 * Return the user's session, throws an exception if there's a problem with the user's session.
	 * @param authtoken
	 * @return
	 */
	public RULUser getSession(String authtoken) throws Exception{
		if(authmap.containsKey(authtoken))
			if(authmap.get(authtoken).timeOut()){
				authmap.remove(authtoken);
				throw new Exception("Session has timed out.");
			}
		if(!authmap.containsKey(authtoken))
			throw new Exception("AuthToken does not match an existing session.");
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
