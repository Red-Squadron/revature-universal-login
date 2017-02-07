package services;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.session.RULUser;
import com.revature.session.SessionManagement;

import dao.UserDAO;

public class LoginService {

	private static Logger logger;

	private LoginService(){

	}

	/**
	 * Validates login for a user.
	 * @param email Provide unique email.
	 * @param password Provide current password.
	 * @return String true for correct input, String false for no match in database.
	 * @throws IOException
	 */
	public static void login(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String responseJson;
		UserDAO dao = UserDAO.getUserDAO();
		RULUser usr = dao.validateLogin(request.getParameter("userName"),
				request.getParameter("password"));

		if(usr == null) {
			responseJson = "{ \"valid\": \"false\" }"; // all values need to be surrounded with double quotes
		}
		else {
			SessionManagement sess = SessionManagement.getSessionManager();
			String authTkn = sess.createSession(usr);

			responseJson = "{ \"valid\": \"true\", " +
							"\"authTkn\": \"" + authTkn +
							"\", \"authLvl\": \"" + usr.getAuthlevel() + "\" }";
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
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
