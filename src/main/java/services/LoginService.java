package services;

import dao.UserDAO;

public class LoginService {
	/**
	 * Validates login for a user.
	 * @param email Provide unique email.
	 * @param password Provide current password.
	 * @return String true for correct input, String false for no match in database.
	 */
	public static String login(String userName, String password){
		
		UserDAO dao = UserDAO.getUserDAO();
		Boolean isValid = dao.validateLogin(userName, password);
		return isValid.toString();
	}
}
