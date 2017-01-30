package dao;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeSuite;
import org.testng.Assert;

public class DaoTest {
	UserDAO dao = null;
	@Test
	public void testSingletonCreation() {
		UserDAO dao2 = UserDAO.getUserDAO();
		Assert.assertEquals(dao, dao2);
	}

	@Test(dependsOnMethods = { "testSingletonCreation" })
	public void testCheckUserExistence() {
		String email = "davidgr@email.com";
		String firstName = "david";
		String lastName = "green";
		String permission = dao.checkUserExistence(email, firstName, lastName);
		Assert.assertTrue(permission.equals("associate") || permission.equals("trainer") || permission.equals("admin"));
	}
	
	@Test(dependsOnMethods = { "testCheckUserExistence" })
	public void testRegisterUser() {
		String email = "davidgr@email.com"; 
		String firstName = "david";
		String middleName = "blue";
		String lastName = "green";
		String phone = "1112223333";
		String password = "dbg";
		Assert.assertTrue(dao.registerUser(email, firstName, middleName, lastName, phone, password));
	}
	
	@Test(dependsOnMethods = { "testRegisterUser" })
	public void testValidateLogin() {
		String email = "davidgr@email.com";
		String password = "dbg";
		Assert.assertTrue(dao.validateLogin(email, password));
	}
	
	@Test(dependsOnMethods = { "testValidateLogin" })
	public void testUpdatePassword() {
		String email = "davidgr@email.com";
		String password = "dbg1!";
		Assert.assertTrue(dao.updatePassword(email, password));
	}
	
	@Test(dependsOnMethods = { "testUpdatePassword" })
	public void testUpdatePhone() {
		String email = "davidgr@email.com";
		String number = "1231231234";
		Assert.assertTrue(dao.updatePhone(email, number));
	}
	
	@Test(dependsOnMethods = { "testUpdatePhone" })
	public void testDeleteUser() {
		String email = "davidgr@email.com";
		Assert.assertTrue(dao.deleteRegisteredUser(email));
	}

	@BeforeSuite
	public void beforeSuite() {
		dao = UserDAO.getUserDAO();
	}

}
