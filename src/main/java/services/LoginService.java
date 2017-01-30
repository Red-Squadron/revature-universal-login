package services;

import java.util.StringTokenizer;

import dao.UserDAO;

public class LoginService {
	public static boolean login(String userName, String password){
		
		UserDAO dao = UserDAO.getUserDAO();
		Boolean isValid = dao.validateLogin(userName, password);
		return isValid;
	}
}
