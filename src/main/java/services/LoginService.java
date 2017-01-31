package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.session.RULUser;
import com.revature.session.SessionManagement;

import dao.UserDAO;

public class LoginService {
	/**
	 * Validates login for a user.
	 * @param email Provide unique email.
	 * @param password Provide current password.
	 * @return String true for correct input, String false for no match in database.
	 * @throws IOException 
	 */
	public static void login(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String responseJson = "";
		UserDAO dao = UserDAO.getUserDAO();
		RULUser usr = dao.validateLogin(request.getParameter("userName"),
				request.getParameter("password"));
		
		
		
		if(usr == null) {
			responseJson = "{valid: false}";
		}
		else {
			SessionManagement sess = SessionManagement.getSessionManager();
			String authTkn = sess.createSession(usr);
			//TODO generate a cookie holding the authTkn
			responseJson = "{valid: true, ";
			responseJson += "authTkn: " + authTkn;
			responseJson += ", authLvl: " + usr.authlevel + "}";
		}
		
		response.setContentType("json");
		PrintWriter out = response.getWriter();
		out.write(responseJson);
		out.flush();
		out.close();
	}
}
