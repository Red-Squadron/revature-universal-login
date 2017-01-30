package services;

import com.revature.RULexceptions.NoSuchSessionException;
import com.revature.RULexceptions.SessionManagementException;
import com.revature.RULexceptions.SessionTimeOutException;
import com.revature.session.RULUser;
import com.revature.session.SessionManagement;

public class AuthenticationService {
	public static String authenticate(String authTkn){
		SessionManagement sessMan = SessionManagement.getSessionManager();
		RULUser userSession;
		String userLvl = null;
		
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
