package services;

import dao.UserDAO;

public class ChangeUserPasswordService {
	public static String changeUserPassword(String userName, String password){
		Boolean success;
		
		UserDAO dao = UserDAO.getUserDAO();
		success = dao.updatePassword(userName, password);
		
		return success.toString();
	}
}
