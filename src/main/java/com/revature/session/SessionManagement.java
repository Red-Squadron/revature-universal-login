package com.revature.session;

import java.util.HashMap;


/**
 * Class containing session management related methods for use by servlets.
 * @author cavan
 */
public class SessionManagement {

	private HashMap<String, RULUser> authmap;
	
	private SessionManagement manager;
	
	private SessionManagement(){
		authmap = new HashMap<String, RULUser>();
	}
	
	public SessionManagement getSessionManager(){
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
	 * 
	 * @param authtoken
	 * @return
	 */
	public RULUser getSession(String authtoken) throws Exception{
		if(!authmap.containsKey(authtoken))
			throw new Exception("AuthToken does not match an existing session.");
		return authmap.get(authtoken);
	}
	
	/**
	 * Returns True if the authToken is associated with a legitimate user session, returns false if the authtoken is invalid.
	 * @param authToken
	 * @return
	 */
	public boolean authenticate(String authToken){
		if(authmap.containsKey(authToken)){
			RULUser user = authmap.get(authToken);
			if(user.timeOut()){
				authmap.remove(authToken);
				return false;}
			return true;
		}
		return false;
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
