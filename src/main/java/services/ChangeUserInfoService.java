package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.RULexceptions.NoSuchSessionException;
import com.revature.RULexceptions.SessionManagementException;
import com.revature.RULexceptions.SessionTimeOutException;
import com.revature.session.RULUser;
import com.revature.session.SessionManagement;

import dao.UserDAO;

public class ChangeUserInfoService {

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
			
			switch(request.getParameter("infoType")){
				case "phone": success = Boolean.toString(dao.updatePhone(userName, request.getParameter("newInfo")));
					break;
				default:
					//should go 404?
					break;
			}
			
		} catch (NoSuchSessionException e) {
			success = "notLoggedIn";
		} catch (SessionTimeOutException e) {
			success ="timedOut";
		} catch (SessionManagementException e) {
			success = "borked";
		}
		
		response.setContentType("html/text");
		PrintWriter out = response.getWriter();
		out.write(success);
		out.flush();
		out.close();
	}
}
