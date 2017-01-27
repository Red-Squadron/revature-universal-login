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
			CallableStatement checkLogin = conn.prepareCall("{call validate_login(?,?)}");

			checkLogin.setString(1, userName);
			checkLogin.setString(2, password);
			checkLogin.executeUpdate();

			if (checkLogin.getInt(1) == 1) {
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
		try{
			CallableStatement registerUser = conn.prepareCall("{call add_new_user(?, ?, ?, ?, ?, ?)}");
			
			registerUser.setString(1, email);
			registerUser.setString(2, fname);
			registerUser.setString(3, mname);
			registerUser.setString(4, lname);
			registerUser.setInt(5, phone);
			registerUser.setString(6, pass);
			
			if (registerUser.getInt(1) == 1) {
				return true;
			} else {
				return false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
