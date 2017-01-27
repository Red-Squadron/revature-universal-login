package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

public class UserDAO {
	private Connection conn;
	private static UserDAO singleton = null;
	
	private UserDAO(){
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String username = "username";
		String pass = "password";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			
			conn = DriverManager.getConnection(url,username,pass);
		} catch (SQLException e) {
			System.out.println("Failed to connect to database at url : " + url);
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to find oracle driver");
		}
	}
	
	public UserDAO getUserDAO(){
		if (singleton == null)
			singleton = new UserDAO();
		return singleton;
	}
	
	public boolean validateLogin(String userName, String password){
		try{
			CallableStatement checkLogin = conn.prepareCall("{? = validate_login(?,?)}");
			
			checkLogin.registerOutParameter(1,  Types.BOOLEAN);
			checkLogin.setString(2, userName);
			checkLogin.setString(3, password);
			checkLogin.executeUpdate();
			
			return checkLogin.getBoolean(1);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
