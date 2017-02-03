package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

public class RegistrationService {
	public static void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
		UserDAO dao = UserDAO.getUserDAO();
		Boolean success;

		success = dao.registerUser(request.getParameter("userEmail"), request.getParameter("firstName"),
				request.getParameter("middleName"), request.getParameter("lastName"),
				request.getParameter("phoneNumber"), request.getParameter("password"));

		response.setContentType("html/text");
		PrintWriter out = response.getWriter();
		out.write(success.toString());
		out.flush();
		out.close();
	}
}
