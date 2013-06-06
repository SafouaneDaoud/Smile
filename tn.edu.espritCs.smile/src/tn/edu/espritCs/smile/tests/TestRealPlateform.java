package tn.edu.espritCs.smile.tests;

import junit.framework.Assert;

import org.junit.Test;

import tn.edu.espritCs.smile.dao.UserDao;
import tn.edu.espritCs.smile.domain.User;
import tn.edu.espritCs.smile.technical.UtilJdbc;

public class TestRealPlateform {
	private UserDao userDao = new UserDao();

	@Test
	public void testAddUser() {
		User user = new User("Ali", "ben salah", "Admin", "198",
				"salah@bensalah.com");
		Assert.assertTrue(userDao.addUser(user));
	}

	@Test
	public void testGetConnetion() {
		UtilJdbc utilJdbc = new UtilJdbc();
		utilJdbc.GetConnetion();
	}

	@Test
	public void testFindUserById() {
		User user = userDao.findUserById(1);
		System.out.println("FirstNameUser: " + user.getFirstNameUser());
		System.out.println("LastNameUser: " + user.getLastNameUser());
		System.out.println("RoleUser: " + user.getRoleUser());
		System.out.println("TelUser: " + user.getTelUser());
		System.out.println("EMailUser: " + user.getEmailUser());
		Assert.assertEquals("Ali", user.getFirstNameUser());
	}

	@Test
	public void testDeleteUserById() {
		User user = userDao.findUserById(1);
		System.out.println(user.getFirstNameUser());
		Assert.assertTrue(userDao.deleteUserById(1));
	}

	@Test
	public void testUpdateUser() {
		User user = userDao.findUserById(1);
		System.out.println("old one :" + user.getFirstNameUser());
		user.setFirstNameUser("Ali");
		System.out.println("new one :" + user.getFirstNameUser());
		Assert.assertTrue(userDao.updateUser(user, "firstNameUser"));
	}
}
