package tn.edu.espritCs.smile.tests;

import junit.framework.Assert;

import org.junit.Test;

import tn.edu.espritCs.smile.dao.SponsorshipDao;
import tn.edu.espritCs.smile.dao.UserDao;
import tn.edu.espritCs.smile.dao.WishDao;
import tn.edu.espritCs.smile.domain.Sponsorship;
import tn.edu.espritCs.smile.domain.User;
import tn.edu.espritCs.smile.domain.Wish;
import tn.edu.espritCs.smile.services.LoginService;
import tn.edu.espritCs.smile.technical.UtilJdbc;

public class TestRealPlateform {
	private UserDao userDao = new UserDao();
	private WishDao wishDao = new WishDao();
	private SponsorshipDao sponsorshipDao = new SponsorshipDao();

	@Test
	public void testAddUser() {
		User user = new User("Mohamed", "ben salah", "Child", "198",
				"salah@bensalah.com", "Login", "Password");
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
		System.out.println("LoginUser: " + user.getLoginUser());
		System.out.println("PasswordUser: " + user.getPasswordUser());
		Assert.assertEquals("Ali", user.getFirstNameUser());
	}

	@Test
	public void testDeleteUserById() {
		User user = userDao.findUserById(1);
		System.out.println(user.getFirstNameUser());
		Assert.assertTrue(userDao.deleteUserById(1));
	}

	@Test
	public void testUpdateUserByField() {
		User user = userDao.findUserById(1);
		System.out.println("old one :" + user.getFirstNameUser());
		user.setFirstNameUser("Ali");
		System.out.println("new one :" + user.getFirstNameUser());
		Assert.assertTrue(userDao.updateUserByField(user, "firstNameUser"));
	}

	@Test
	public void testUpdateUser() {
		User user = userDao.findUserById(1);
		System.out.println("old one :" + user.getFirstNameUser());
		user.setFirstNameUser("Ali");
		System.out.println("new one :" + user.getFirstNameUser());
		Assert.assertTrue(userDao.updateUser(user));
	}

	@Test
	public void testAddWish() {
		Wish wish = new Wish("Je veux un cong�s", "Requested", 1, 2);
		Assert.assertTrue(wishDao.addWish(wish));
	}

	@Test
	public void testFindWishById() {
		Wish wish = wishDao.findWishById(1);
		System.out.println("DescriptionWish: " + wish.getDescriptionWish());
		System.out.println("StatusWish: " + wish.getStatusWish());
		System.out.println("IdUserChild: " + wish.getIdUserChild());
		System.out.println("IdUserDonor: " + wish.getIdUserDonor());
		Assert.assertEquals("Je veux un cong�s", wish.getDescriptionWish());
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
		Assert.assertTrue(wishDao.updateWish(wish));
	}

	@Test
	public void testAddSponsorship() {
		Sponsorship sponsorship = new Sponsorship(2, 3);
		Assert.assertTrue(sponsorshipDao.addSponsorship(sponsorship));
	}

	@Test
	public void testFindSponsorshipById() {
		Sponsorship sponsorship = sponsorshipDao.findSponsorshipById(1);
		System.out.println("IdUserChild: " + sponsorship.getIdUserChild());
		System.out.println("IdUserDonor: " + sponsorship.getIdUserDonor());
		Assert.assertEquals(1, sponsorship.getIdUserChild());
	}

	@Test
	public void testFindSponsorshipByDonorChildIds() {
		Sponsorship sponsorship = sponsorshipDao
				.findSponsorshipByDonorChildIds(3, 2);
		System.out.println("IdUserChild: " + sponsorship.getIdUserChild());
		System.out.println("IdUserDonor: " + sponsorship.getIdUserDonor());
		Assert.assertEquals(2, sponsorship.getIdUserChild());
	}

	@Test
	public void testDeleteSponsorshipById() {
		Sponsorship sponsorship = sponsorshipDao.findSponsorshipById(1);
		System.out.println(sponsorship.getIdUserChild());
		Assert.assertTrue(sponsorshipDao.deleteSponsorshipById(1));
	}

	@Test
	public void testUpdateSponsorship() {
		Sponsorship sponsorship = sponsorshipDao.findSponsorshipById(1);
		System.out.println("old one :" + sponsorship.getIdUserChild());
		sponsorship.setIdUserChild(2);
		System.out.println("new one :" + sponsorship.getIdUserChild());
		Assert.assertTrue(sponsorshipDao.updateSponsorship(sponsorship));
	}

	@Test
	public void testLogin() {
		LoginService loginService = new LoginService();
		Assert.assertTrue(loginService.login("Ali", "Ali") != null);
	}
}
