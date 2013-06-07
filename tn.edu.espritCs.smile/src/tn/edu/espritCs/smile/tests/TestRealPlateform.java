package tn.edu.espritCs.smile.tests;

import junit.framework.Assert;

import org.junit.Test;

import tn.edu.espritCs.smile.dao.SponsorshipDao;
import tn.edu.espritCs.smile.dao.UserDao;
import tn.edu.espritCs.smile.dao.WishDao;
import tn.edu.espritCs.smile.domain.User;
import tn.edu.espritCs.smile.domain.Wish;
import tn.edu.espritCs.smile.technical.UtilJdbc;

public class TestRealPlateform {
	private UserDao userDao = new UserDao();
	private WishDao wishDao = new WishDao();
	private SponsorshipDao sponsorshipDao = new SponsorshipDao();

	@Test
	public void testAddUser() {
		User user = new User("Mohamed", "ben salah", "Child", "198",
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
	
	@Test
	public void testAddWish() {
		Wish wish = new Wish("Je veux un congès", "Requested", 1, 2);
		Assert.assertTrue(wishDao.addWish(wish));
	}

	@Test
	public void testFindWishById() {
		Wish wish = wishDao.findWishById(1);
		System.out.println("DescriptionWish: " + wish.getDescriptionWish());
		System.out.println("StatusWish: " + wish.getStatusWish());
		System.out.println("IdUserChild: " + wish.getIdUserChild());
		System.out.println("IdUserDonor: " + wish.getIdUserDonor());
		Assert.assertEquals("Je veux un congès", wish.getDescriptionWish());
	}

	@Test
	public void testDeleteWishById() {
		Wish wish = wishDao.findWishById(1);
		System.out.println(wish.getDescriptionWish());
		Assert.assertTrue(wishDao.deleteWishById(1));
	}

	@Test
	public void testUpdateWish() {
		Wish wish = wishDao.findWishById(1);
		System.out.println("old one :" + wish.getDescriptionWish());
		wish.setDescriptionWish("Ali");
		System.out.println("new one :" + wish.getDescriptionWish());
		Assert.assertTrue(wishDao.updateWish(wish, "descriptionWish"));
	}
}
