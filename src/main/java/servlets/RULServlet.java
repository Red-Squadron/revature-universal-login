package servlets;

import java.io.IOException;
import java.io.PrintWriter;

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
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RULServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String responseString = "noResponse";
		System.out.println("post");
		String requestedService = request.getRequestURI().substring(37);
		System.out.println("requestedService : " + requestedService);
		
		switch(requestedService){
			case "login": //LoginService.login(request, response);
			System.out.println("login");
				break;
			case "register": /*responseString = RegistrationService.register(
						request.getParameter("userEmail"), request.getParameter("firstName"),
						request.getParameter("middleName"), request.getParameter("lastName"),
						request.getParameter("phoneNumber"), request.getParameter("password"));*/
				System.out.println("registering : "+
						request.getParameter("userEmail")+ request.getParameter("firstName")+
						request.getParameter("middleName")+ request.getParameter("lastName")+
						request.getParameter("phoneNumber")+ request.getParameter("password"));
				break;
			case "authenticate": //responseString = AuthenticationService.authenticate(
						//request.getParameter("authTkn"));
				break;
			case "changeUserInfo": responseString = ChangeUserInfoService.changeUserInfo(
						request.getParameter("authTkn"), "phone", request.getParameter("password"));
				break;
			case "changeUserPassword": responseString = ChangeUserPasswordService.changeUserPassword(
						request.getParameter("userEmail"), request.getParameter("password"));
				break;
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write(responseString);
		out.flush();
		out.close();
	}

}