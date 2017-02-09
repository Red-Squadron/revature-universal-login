package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Logger;
import java.util.logging.Level;

import org.mindrot.jbcrypt.BCrypt;

import com.revature.session.RULUser;

/**
 * Singleton Class for accessing RUL database.
 * @author David
 *
 */
public class UserDAO {
	private Connection conn;
	private static UserDAO singleton = null;
	private Logger logger;

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
			logger.log(Level.SEVERE, "\nFailed to connect to database at url : " + url, e);
		} catch (ClassNotFoundException e) {
			logger.log(Level.SEVERE, "\nFailed to find oracle driver", e);
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
	 * Validates login for a user and create a RULUser token for authentication.
	 * @param email Provide unique email.
	 * @param password Provide current password.
	 * @return RULUser object with user info if successful, returns null object if not successful
	 */
	public RULUser validateLogin(String email, String password) {
		RULUser user = null;
		try {
			PreparedStatement checkLogin = conn.prepareStatement("select * from userregistration where useremail = ?");
			checkLogin.setString(1, email);
			ResultSet rs = checkLogin.executeQuery();
			if(rs.next()) {
					String hashedpw = rs.getString("passwd");
					if(BCrypt.checkpw(password, hashedpw)){

						//Create RULUser object for token
						user = new RULUser();
						user.setEmailaddress(rs.getString("userEmail"));
						user.setFirstname(rs.getString("firstName"));
						user.setLastname(rs.getString("lastName"));
						user.setMiddlename(rs.getString("middleName"));
						user.setAuthlevel(rs.getString("permissions"));

						rs.close();
						checkLogin.close();
						return user;
					}
			} else {
					rs.close();
					checkLogin.close();
					return user;
			}

			rs.close();
			checkLogin.close();
		} catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
		return user;
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
	 * TODO this function should return a string the describes a failure
	 */
	public boolean registerUser(String email, String firstName, String middleName, String lastName, String phone, String password) {

		String permission = "";
		try {
			permission = checkUserExistence(email, firstName, lastName);
		} catch (SQLException e1) {
			logger.log(Level.SEVERE, e1.getMessage(),e1);
		}

		if ("".equals(permission))
			return false;

		try {
				CallableStatement registerUser = conn.prepareCall("{call add_new_user(?, ?, ?, ?, ?, ?, ?, ?)}");

				registerUser.setString(1, email);
				registerUser.setString(2, firstName);
				registerUser.setString(3, middleName);
				registerUser.setString(4, lastName);
				registerUser.setString(5, phone);
				String hash = BCrypt.hashpw(password, BCrypt.gensalt());
				registerUser.setString(6, hash);
				registerUser.setString(7, permission);
				registerUser.registerOutParameter(8, Types.INTEGER);
				registerUser.executeUpdate();

				if (registerUser.getInt(8) == 1) {
					registerUser.close();
					return true;
				} else {
					registerUser.close();
					return false;
				}
		} catch(SQLException e) {
			logger.log(Level.SEVERE, e.getMessage(),e);
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

			PreparedStatement checkPassword = conn.prepareStatement("select passwd from userregistration where useremail=?");
			checkPassword.setString(1, email);
			ResultSet rs = checkPassword.executeQuery();
			rs.next();
			String oldpass = rs.getString(1);

			//Reject same password
			if(BCrypt.checkpw(password, oldpass)) {
				rs.close();
				checkPassword.close();
				return false;
			}

			checkPassword = conn.prepareStatement("select pastpass from passwordhistory where useremail = ?");
			checkPassword.setString(1, email);
			rs = checkPassword.executeQuery();

			int totalpasswords = 0;
			while(rs.next()){
				totalpasswords++;
				if(BCrypt.checkpw(password, rs.getString(1))){
					rs.close();
					checkPassword.close();
					return false;
				}
			}

			rs.close();
			checkPassword.close();

			// TODO Implement a trigger to delete the 3rd oldest value from password history

			PreparedStatement updatePassQuery = conn.prepareStatement("update userregistration set passwd = ? where useremail = ?");
			String hash = BCrypt.hashpw(password, BCrypt.gensalt());
			updatePassQuery.setString(1, hash);
			updatePassQuery.setString(2, email);
			updatePassQuery.execute();
			updatePassQuery.close();

			//If there are more than 3 results from passhistory, delete the oldest one.
			if(totalpasswords>=3){
				updatePassQuery = conn.prepareStatement("delete (select * from passhistory where useremail = ? order by add_stamp asc) where row_num<=1");
				updatePassQuery.setString(1, email);
				updatePassQuery.execute();
				updatePassQuery.close();
			}

			updatePassQuery = conn.prepareStatement("insert into passhistory (useremail, pastpass, add_stamp) values (?, ?, systimestamp");
			updatePassQuery.setString(1, email);
			updatePassQuery.setString(2, oldpass);
			updatePassQuery.execute();
			updatePassQuery.close();

		} catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage(),e);
		} catch (NullPointerException e) {
			logger.log(Level.SEVERE, "Null pointer", e);
		}
		return false;
	}

	/**
	 * Updates the current phone number associated with given email.
	 * @param email Provide unique email.
	 * @param number Provide new phone number as String.
	 * @return true if phone number is updated, false if phone number is not updated.
	 * @throws SQLException
	 */
	public boolean updatePhone(String email, String number) throws SQLException {
		CallableStatement updatePhone = conn.prepareCall("{call update_phone(?,?,?)}");
		try {
			updatePhone.setString(1, email);
			updatePhone.setString(2, number);
			updatePhone.registerOutParameter(3, Types.INTEGER);
			updatePhone.executeUpdate();

			if (updatePhone.getInt(3) == 1) {
				updatePhone.close();
				return true;
			} else {
				updatePhone.close();
				return false;
			}
		} catch (SQLException e) {
			updatePhone.close();
			logger.log(Level.SEVERE, e.getMessage(),e);
		}
		return false;
	}

	/**
	 * Checks against salesforce table to see if the combination of email, firstName, lastName exist.
	 * @param email Provide unique email.
	 * @param firstName Provide first name.
	 * @param lastName Provide last name.
	 * @return Permission level of checked user if user exist, otherwise empty string "".
	 * @throws SQLException 
	 */
	public String checkUserExistence(String email, String firstName, String lastName) throws SQLException {
		CallableStatement checkUser = conn.prepareCall("{call check_user_existence(?, ?, ?, ?, ?)}");
		try {
			checkUser.setString(1, email);
			checkUser.setString(2, firstName);
			checkUser.setString(3, lastName);
			checkUser.registerOutParameter(4, Types.VARCHAR); // permission level
			checkUser.registerOutParameter(5, Types.INTEGER);
			checkUser.executeUpdate();

			if (checkUser.getInt(5) == 1) {
				String retval = checkUser.getString(4);
				checkUser.close();
				return retval;
			} else {
				checkUser.close();
				return "";
			}
		} catch (SQLException e) {
			checkUser.close();
			logger.log(Level.SEVERE, e.getMessage(),e);
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
				deleteUser.close();
				return true;
			} else {
				deleteUser.close();
				return false;
			}
		} catch (SQLException e) {
			logger.log(Level.SEVERE, e.getMessage(),e);
		}

		return false;
	}

	public Logger getlogger() {
		return logger;
	}

	public void setlogger(Logger l) {
		logger = l;
	}
}
