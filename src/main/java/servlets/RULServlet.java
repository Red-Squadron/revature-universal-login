package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.AuthenticationService;
import services.ChangeUserInfoService;
import services.ChangeUserPasswordService;
import services.LoginService;
import services.RegistrationService;

/**
 * Servlet implementation class RULServlet
 */
public class RULServlet extends HttpServlet {

	private static final long serialVersionUID = 8601301781547689945L;

	/**
     * @see HttpServlet #HttpServlet
     */
    public RULServlet() {
        super();
    }

	/**
	 * @see HttpServlet #doGet
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet #doPost
	 */
    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestedService = request.getRequestURI().substring(37);
		
		switch(requestedService){
			case "login": LoginService.login(request, response);
				break;
			case "register": RegistrationService.register(request, response);
				break;
			case "authenticate": AuthenticationService.authenticate(request, response);
				break;
			case "changeUserInfo": ChangeUserInfoService.changeUserInfo(request, response);
				break;
			case "changeUserPassword": ChangeUserPasswordService.changeUserPassword(request, response);
				break;
		}
	}

}