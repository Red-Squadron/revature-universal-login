package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class UserDAO {
	private Connection conn;
	private static UserDAO singleton = null;

	private UserDAO() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "username";
		String pass = "password";
		try {
			Class.forName("oracle.jdbc.OracleDriver");

			conn = DriverManager.getConnection(url, username, pass);
		} catch (SQLException e) {
			System.out.println("Failed to connect to database at url : " + url);
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to find oracle driver");
		}
	}

	public UserDAO getUserDAO() {
		if (singleton == null)
			singleton = new UserDAO();
		return singleton;
	}

	public boolean validateLogin(String userName, String password) {
		try {
			CallableStatement checkLogin = conn.prepareCall("{call validate_login(?,?,?)}");

			checkLogin.setString(1, userName);
			checkLogin.setString(2, password);
			checkLogin.registerOutParameter(3, Types.INTEGER);
			checkLogin.executeUpdate();

			if (checkLogin.getInt(3) == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean registerUser(String email, String fname, String mname, String lname, int phone, String pass) {
		String permission = checkUserExistence(email, fname, lname);
		if (permission == "")
			return false;
		try{
			CallableStatement registerUser = conn.prepareCall("{call add_new_user(?, ?, ?, ?, ?, ?, ?, ?)}");
			
			registerUser.setString(1, email);
			registerUser.setString(2, fname);
			registerUser.setString(3, mname);
			registerUser.setString(4, lname);
			registerUser.setInt(5, phone);
			registerUser.setString(6, pass);
			registerUser.setString(7, permissions);
			registerUser.registerOutParameter(8, Types.INTEGER);
			registerUser.executeUpdate();
			
			if (registerUser.getInt(8) == 1) {
				return true;
			} else {
				return false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean checkPassword(String userName, String password) {
		try {
			CallableStatement checkPassword = conn.prepareCall("{call paswd_checker(?,?,?)}");

			checkPassword.setString(1, userName);
			checkPassword.setString(2, password);
			checkPassword.registerOutParameter(3, Types.INTEGER);
			checkPassword.executeUpdate();

			if (checkPassword.getInt(3) == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updatePhone(String email, int number) {
		try {
			CallableStatement updatePhone = conn.prepareCall("{call update_phone(?,?,?)}");

			updatePhone.setString(1, email);
			updatePhone.setInt(2, number);
			updatePhone.registerOutParameter(3, Types.INTEGER);
			updatePhone.executeUpdate();

			if (updatePhone.getInt(3) == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public String checkUserExistence(String email, String fname, String lname) {
		try {
			CallableStatement checkUser = conn.prepareCall("{call check_user_existence(?, ?, ?, ?, ?)}");
			
			checkUser.setString(1, email);
			checkUser.setString(2, fname);
			checkUser.setString(3, mname);
			checkUser.registerOutParameter(4, Types.VARCHAR);
			checkUser.registerOutParameter(5, Types.INTEGER);
			checkUser.executeUpdate();

			if (checkUser.getInt(5) == 1) {
				return (String) checkUser.getString(4);
			} else {
				return "";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
