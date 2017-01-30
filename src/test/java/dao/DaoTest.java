package dao;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeSuite;
import org.testng.Assert;

public class DaoTest extends Assert{
	UserDAO dao = null;
	@Test(priority = -10)
	public void testSingletonCreation() {
		UserDAO dao2 = UserDAO.getUserDAO();
		assertEquals(dao, dao2);
	}

	@Test(priority = -9)
	public void testCheckUserExistence() {
		String email = "davidgr@email.com";
		String firstName = "david";
		String lastName = "green";
		String permission = dao.checkUserExistence(email, firstName, lastName);
		assertTrue(permission.equals("associate") || permission.equals("trainer") || permission.equals("admin"));
	}
	
	@Test(priority = -8)
	public void testRegisterUser() {
		String email = "davidgr@email.com"; 
		String firstName = "david";
		String middleName = "blue";
		String lastName = "green";
		String phone = "1112223333";
		String password = "dbg";
		assertTrue(dao.registerUser(email, firstName, middleName, lastName, phone, password));
	}
	
	@Test(priority = -7)
	public void testValidateLogin() {
		String email = "davidgr@email.com";
		String password = "dbg";
		assertTrue(dao.validateLogin(email, password));
	}
	
	@Test(priority = -6)
	public void testUpdatePassword() {
		String email = "davidgr@email.com";
		String password = "dbg1!";
		assertTrue(dao.updatePassword(email, password));
	}
	
	@Test(priority = -6)
	public void testUpdatePhone() {
		String email = "davidgr@email.com";
		String number = "1231231234";
		assertTrue(dao.updatePhone(email, number));
	}
	
	@Test(priority = 0)
	public void testDeleteUser() {
		String email = "davidgr@email.com";
		assertTrue(dao.deleteRegisteredUser(email));
	}

	@BeforeSuite
	public void beforeSuite() {
		dao = UserDAO.getUserDAO();
	}

}
