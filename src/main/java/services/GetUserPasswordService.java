package services;

import dao.UserDAO;

public class GetUserPasswordService {
	public String getUserPassword(String userEmail){
		UserDAO dao = UserDAO.getUserDAO();
		
		String success = Boolean.toString(dao.getPassword(userEmail));
		
		return success;
	}
}
