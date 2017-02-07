package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.RULexceptions.NoSuchSessionException;
import com.revature.RULexceptions.SessionManagementException;
import com.revature.RULexceptions.SessionTimeOutException;
import com.revature.session.RULUser;
import com.revature.session.SessionManagement;

import dao.UserDAO;

public class ChangeUserInfoService {

	private static Logger LOGGER;
	
	private ChangeUserInfoService(){
		
	}
	
	public static void changeUserInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserDAO dao = UserDAO.getUserDAO();
		SessionManagement sessMan = SessionManagement.getSessionManager();
		
		RULUser userSession;
		String success = "noResponse";
		String userName;
		
		try {
			userSession = sessMan.getSession(request.getParameter("authTkn"));
			userName = userSession.getEmailaddress();
			
			if("phone".equals(request.getParameter("infoType"))){
				success = Boolean.toString(dao.updatePhone(userName, request.getParameter("newInfo")));
			}
			
		} catch (NoSuchSessionException e) {
			LOGGER.log(Level.SEVERE, e.getMessage() + " : notLoggedIn", e);
			success = "notLoggedIn";
		} catch (SessionTimeOutException e) {
			LOGGER.log(Level.FINE, e.getMessage() + " : timedOut", e);
			success ="timedOut";
		} catch (SessionManagementException e) {
			LOGGER.log(Level.SEVERE, e.getMessage() + " : borked", e);
			success = "borked";
		}
		
		response.setContentType("html/text");
		PrintWriter out = response.getWriter();
		out.write(success);
		out.flush();
		out.close();
	}

	public static Logger getLOGGER() {
		return LOGGER;
	}

	public static void setLOGGER(Logger lOGGER) {
		LOGGER = lOGGER;
	}
}
