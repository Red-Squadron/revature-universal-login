package servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.RULexceptions.NoSuchSessionException;
import com.revature.RULexceptions.SessionManagementException;
import com.revature.RULexceptions.SessionTimeOutException;
import com.revature.session.RULUser;
import com.revature.session.SessionManagement;

import dao.UserDAO;

/**
 * Servlet implementation class AuthenticationServlet
 */
public class AuthenticationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputStream is = request.getInputStream();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		byte[] buf = new byte[32];
		int r = 0;
		while(r >= 0)
		{
			r = is.read(buf);
			if(r >= 0)
				os.write(buf, 0, r);
		}
		String authTkn = new String(os.toByteArray(), "UTF-8");
		
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
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write(userLvl);
		out.flush();
		out.close();
	}

}
