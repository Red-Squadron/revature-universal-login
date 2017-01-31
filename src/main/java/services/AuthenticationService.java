package services;

import com.revature.RULexceptions.NoSuchSessionException;
import com.revature.RULexceptions.SessionManagementException;
import com.revature.RULexceptions.SessionTimeOutException;
import com.revature.session.RULUser;
import com.revature.session.SessionManagement;

public class AuthenticationService {
	/**
	 * Authenticates authorization token against active sessions.
	 * @param authTkn token to validate.
	 * @return user authorization level if valid, error code if invalid.
	 */
	public static String authenticate(String authTkn){
		SessionManagement sessMan = SessionManagement.getSessionManager();
		RULUser userSession;
		String userLvl = "noResponse";
		
		try {
			userSession = sessMan.getSession(authTkn);
			userLvl = userSession.authlevel;
		} catch (NoSuchSessionException e) {
			userLvl = "notLoggedIn";
		} catch (SessionTimeOutException e) {
			userLvl ="timedOut";
		} catch (SessionManagementException e) {
			userLvl = "borked";
		}
		
		return userLvl;
	}
}
