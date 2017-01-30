package dao;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeSuite;
import org.testng.Assert;

public class DaoTest {
	UserDAO dao = null;
	@Test
	public void testSingletonCreation() {
		UserDAO dao2 = UserDAO.getUserDAO();
		Assert.assertTrue(dao == dao2);
	}

	@Test
	public void testCheckUserExistence() {
		String email = "davidgr@email.com";
		String firstName = "david";
		String lastName = "green";
		String permission = dao.checkUserExistence(email, firstName, lastName);
		Assert.assertTrue(permission == "associate" || permission == "trainer" || permission == "admin");
	}
	
	@Test
	public void testRegisterUser() {
		String email = "davidgr@email.com"; 
		String firstName = "david";
		String middleName = "blue";
		String lastName = "green";
		String phone = "1112223333";
		String password = "dbg";
		Assert.assertTrue(dao.registerUser(email, firstName, middleName, lastName, phone, password));
	}
	
	@Test
	public void testValidateLogin() {
		String email = "davidgr@email.com";
		String password = "dbg";
		Assert.assertTrue(dao.validateLogin(email, password));
	}
	
	@Test
	public void testUpdatePassword() {
		String email = "davidgr@email.com";
		String password = "dbg1!";
		Assert.assertTrue(dao.updatePassword(email, password));
	}
	
	@Test
	public void testUpdatePhone() {
		String email = "davidgr@email.com";
		String number = "1231231234";
		Assert.assertTrue(dao.updatePhone(email, number));
	}

	@BeforeSuite
	public void beforeSuite() {
		dao = UserDAO.getUserDAO();
	}

}
