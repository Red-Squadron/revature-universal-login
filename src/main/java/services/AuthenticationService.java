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

public class AuthenticationService {
	
	private static Logger logger;
	
	private AuthenticationService(){
		
	}
	
	/**
	 * Authenticates authorization token against active sessions.
	 * @param authTkn token to validate.
	 * @return user authorization level if valid, error code if invalid.
	 * @throws IOException 
	 */
	public static void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException{
		SessionManagement sessMan = SessionManagement.getSessionManager();
		RULUser userSession;
		String responseJson = "{authTkn: noResponse}";
		
		try {
			userSession = sessMan.getSession(request.getParameter("authTkn"));
			responseJson = userSession.getAuthlevel();
			userSession.refresh();
			//TODO refresh the cookie
		} catch (NoSuchSessionException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			responseJson = "{authTkn: notLoggedIn}";
		} catch (SessionTimeOutException e) {
			logger.log(Level.FINE, e.getMessage(), e);
			responseJson ="{authTkn: timedOut}";
		} catch (SessionManagementException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			responseJson = "{authTkn: borked}";
		}

		response.setContentType("json");
		PrintWriter out = response.getWriter();
		out.write(responseJson);
		out.flush();
		out.close();
	}

	public static Logger getlogger() {
		return logger;
	}

	public static void setlogger(Logger l) {
		logger = l;
	}
}
