package services;

import com.revature.RULexceptions.NoSuchSessionException;
import com.revature.RULexceptions.SessionManagementException;
import com.revature.RULexceptions.SessionTimeOutException;
import com.revature.session.RULUser;
import com.revature.session.SessionManagement;

import dao.UserDAO;

public class ChangeUserInfoService {

	public static String changeUserInfo(String authTkn, String infoType, String newInfo) {
		UserDAO dao = UserDAO.getUserDAO();
		SessionManagement sessMan = SessionManagement.getSessionManager();
		
		RULUser userSession;
		String success = "noResponse";
		String userName;
		
		try {
			userSession = sessMan.getSession(authTkn);
			userName = userSession.emailaddress;
			
			switch(infoType){
				case "phone": success = Boolean.toString(dao.updatePhone(userName, newInfo));
					break;
			}
			
			
		} catch (NoSuchSessionException e) {
			success = "notLoggedIn";
		} catch (SessionTimeOutException e) {
			success ="timedOut";
		} catch (SessionManagementException e) {
			success = "borked";
		}
		
		
		return success;
	}
}
