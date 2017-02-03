package services;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

public class ChangeUserPasswordService {
	public static void changeUserPassword(HttpServletRequest request, HttpServletResponse response) throws IOException{
		Boolean success;
		
		UserDAO dao = UserDAO.getUserDAO();
		success = dao.updatePassword(request.getParameter("userName"), request.getParameter("password"));

		response.setContentType("html/text");
		PrintWriter out = response.getWriter();
		out.write(success.toString());
		out.flush();
		out.close();
	}
}
