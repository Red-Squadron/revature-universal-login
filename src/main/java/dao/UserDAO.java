package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Singleton Class for accessing RUL database.
 * @author David
 *
 */
public class UserDAO {
	private Connection conn;
	private static UserDAO singleton = null;

	/**
	 * Private constructor for UserDao class.
	 * Is only called once.
	 */
	private UserDAO() {
		String url = "jdbc:oracle:thin:@ruldb.chueiwozbnfz.us-east-1.rds.amazonaws.com:1521:RULORCL";
		String username = "ruladmin";
		String pass = "ruladminpasskey1611";
		try {
			Class.forName("oracle.jdbc.OracleDriver");

			conn = DriverManager.getConnection(url, username, pass);
		} catch (SQLException e) {
			System.out.println("Failed to connect to database at url : " + url);
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to find oracle driver");
		}
	}

	/**
	 * Creates only one instance of DAO.
	 * @return UserDAO object.
	 */
	public static UserDAO getUserDAO() {
		if (singleton == null)
			singleton = new UserDAO();
		return singleton;
	}

	/**
	 * Validates login for a user.
	 * @param email Provide unique email.
	 * @param password Provide current password.
	 * @return true for correct input, false for no match in database.
	 */
	public boolean validateLogin(String email, String password) {
		try {
			CallableStatement checkLogin = conn.prepareCall("{call validate_login(?,?,?)}");

			checkLogin.setString(1, email);
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

	/**
	 * Will first check against salesforce table to see if the combination of email, firstName, lastName exist.
	 * If the combination exist, the info provided and permission level from salesforce will be registered into RUL database.
	 * @param email Provide unique email.
	 * @param firstName Provide first name.
	 * @param middleName Provide middle name.
	 * @param lastName Provide last name.
	 * @param phone Provide phone number as String.
	 * @param password Provide current password.
	 * @return true if registration is successful, false if registration is not successful.
	 */
	public boolean registerUser(String email, String firstName, String middleName, String lastName, String phone, String password) {
		
		String permission = checkUserExistence(email, firstName, lastName);
		if (permission.equals(""))
			return false;
		
		try{
			CallableStatement registerUser = conn.prepareCall("{call add_new_user(?, ?, ?, ?, ?, ?, ?, ?)}");
			
			registerUser.setString(1, email);
			registerUser.setString(2, firstName);
			registerUser.setString(3, middleName);
			registerUser.setString(4, lastName);
			registerUser.setString(5, phone);
			registerUser.setString(6, password);
			registerUser.setString(7, permission);
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
	
	/**
	 * Updates the password associated with given email if not the same as previous 3 passwords for this email.
	 * @param email Provide unique email.
	 * @param password Provide new password.
	 * @return true if password is accepted, false if password is rejected.
	 */
	public boolean updatePassword(String email, String password) {
		try {
			CallableStatement checkPassword = conn.prepareCall("{call passwd_checker(?,?,?)}");

			checkPassword.setString(1, email);
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
	
	/**
	 * Updates the current phone number associated with given email.
	 * @param email Provide unique email.
	 * @param number Provide new phone number as String.
	 * @return true if phone number is updated, false if phone number is not updated.
	 */
	public boolean updatePhone(String email, String number) {
		try {
			CallableStatement updatePhone = conn.prepareCall("{call update_phone(?,?,?)}");

			updatePhone.setString(1, email);
			updatePhone.setString(2, number);
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
	
	/**
	 * Checks against salesforce table to see if the combination of email, firstName, lastName exist.
	 * @param email Provide unique email.
	 * @param firstName Provide first name.
	 * @param lastName Provide last name.
	 * @return Permission level of checked user if user exist, otherwise empty string "".
	 */
	public String checkUserExistence(String email, String firstName, String lastName) {
		try {
			CallableStatement checkUser = conn.prepareCall("{call check_user_existence(?, ?, ?, ?, ?)}");
			
			checkUser.setString(1, email);
			checkUser.setString(2, firstName);
			checkUser.setString(3, lastName);
			checkUser.registerOutParameter(4, Types.VARCHAR); // permission level
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
		return "";
	}
	
	/**
	 * This deletes password history and user account from RUL database.
	 * Does not affect salesforce.
	 * @param email Provide unique email.
	 * @return true if deletion is successful, false if deletion is not successful.
	 */
	public boolean deleteRegisteredUser(String email){
		try {
			CallableStatement deleteUser = conn.prepareCall("{call delete_user_registration(?,?)}");
			
			deleteUser.setString(1, email);
			deleteUser.registerOutParameter(2, Types.INTEGER);
			deleteUser.executeUpdate();

			if (deleteUser.getInt(2) == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
