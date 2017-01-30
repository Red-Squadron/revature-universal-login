package services;

import dao.UserDAO;

public class RegistrationService {
	public static String register(String userEmail, String firstName, String middleName,
								  String lastName, String phoneNumber, String password)
	{
		Boolean success;
		UserDAO dao = UserDAO.getUserDAO();
		
		success = dao.registerUser(userEmail, firstName, middleName, lastName, phoneNumber, password);
		
		return success.toString();
	}
}
