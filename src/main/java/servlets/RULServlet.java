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
		
		String requestedService = request.getRequestURI().substring(38);
		
		/*InputStream is = request.getInputStream();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		byte[] buf = new byte[32];
		int r = 0;
		while(r >= 0)
		{
			r = is.read(buf);
			if(r >= 0)
				os.write(buf, 0, r);
		}
		String in = new String(os.toByteArray(), "UTF-8");*/
		
		try{
			switch(requestedService){
				case "login": responseString = LoginService.login(
										request.getParameter("userName"), request.getParameter("password"));
					break;
				case "register": responseString = RegistrationService.register(
							request.getParameter("userEmail"), request.getParameter("firstname"),
							request.getParameter("middlename"), request.getParameter("lastname"),
							request.getParameter("phoneNumber"), request.getParameter("password"));
					/* call registerUser() from the DAO
					 * request.getParameter("email/userName")
					 * request.getParameter("firstName")
					 * request.getParameter("middleName")
					 * request.getParameter("lastName")
					 * request.getParameter("phone")
					 * request.getParameter("password")
					 */
					break;
				case "authenticate": AuthenticationService.authenticate(request.getParameter("authTkn"));
					break;
				case "changeUserInfo": ChangeUserInfoService.changeUserInfo(
								request.getParameter("authTkn"), "phone", request.getParameter("password"));
					/* call updatePhone() from the DAO
					 * request.getParameter("email/userName")
					 * request.getParameter("phone")
					 */
					break;
				case "changeUserPassword": ChangeUserPasswordService.changeUserPassword(
							request.getParameter("userEmail"), request.getParameter("password"));
					/* call updatePassword() from the DAO
					 * request.getParameter("email/userName")
					 * request.getParameter("password") // is this the new one only?????
					 */
					break;
			}
		}catch(Exception e){
			responseString = requestedService + " " + e.getMessage();
			
		}
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.write(responseString);
		out.flush();
		out.close();
	}

}