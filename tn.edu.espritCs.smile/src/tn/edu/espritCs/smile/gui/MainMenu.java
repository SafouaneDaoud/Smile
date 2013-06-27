package tn.edu.espritCs.smile.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import tn.edu.espritCs.smile.domain.Wish;
import tn.edu.espritCs.smile.services.ReportingService;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class MainMenu extends JFrame {

	private JPanel contentPane;
	private ReportingService reportingService = new ReportingService();

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmClose = new JMenuItem("Exit");
		mntmClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		mnFile.add(mntmClose);

		JMenu mnViews = new JMenu("Views");
		menuBar.add(mnViews);

		JMenuItem mntmMyAccount = new JMenuItem("My account");
		mntmMyAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountFrame accountFrame = new AccountFrame(
						LoginPage.currentUser);
				accountFrame.setVisible(true);
			}
		});
		mnViews.add(mntmMyAccount);

		JMenuItem mntmManageUsers = new JMenuItem("Manage users");
		mntmManageUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListUsers listUsers = new ListUsers();
				listUsers.setVisible(true);
			}
		});
		mnViews.add(mntmManageUsers);
		mntmManageUsers.setVisible(LoginPage.currentUser.getRoleUser().equals(
				"Admin"));

		JMenuItem mntmAskForA = new JMenuItem("Request a new wish");
		mntmAskForA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Wish wish = new Wish();
				RequestWishFrame requestWishFrame = new RequestWishFrame(wish);
				requestWishFrame.setVisible(true);
			}
		});
		mnViews.add(mntmAskForA);
		mntmAskForA.setVisible(LoginPage.currentUser.getRoleUser().equals(
				"Child"));

		JMenuItem mntmManageWishes = new JMenuItem("View wishes");
		mntmManageWishes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListWishes listWishes = new ListWishes();
				listWishes.setVisible(true);
			}
		});
		mnViews.add(mntmManageWishes);
		mntmManageWishes.setVisible(LoginPage.currentUser.getRoleUser().equals(
				"Admin")
				|| LoginPage.currentUser.getRoleUser().equals("Donor"));

		JMenuItem mntmMyWishes = new JMenuItem("My wishes");
		mntmMyWishes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListWishes listWishes = new ListWishes();
				listWishes.setVisible(true);
			}
		});
		mnViews.add(mntmMyWishes);
		mntmMyWishes.setVisible(LoginPage.currentUser.getRoleUser().equals(
				"Child"));

		JMenuItem mntmSponsorship = new JMenuItem("Sponsorship");
		mntmSponsorship.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SponsorshipForm sponsorshipForm = new SponsorshipForm();
				sponsorshipForm.setVisible(true);
			}
		});
		mnViews.add(mntmSponsorship);
		mntmSponsorship.setVisible(LoginPage.currentUser.getRoleUser().equals(
				"Donor"));

		JMenu mnReporting = new JMenu("Reporting");
		menuBar.add(mnReporting);

		JMenuItem mntmAllUsersList = new JMenuItem("All user's list");
		mntmAllUsersList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				reportingService.generateUsersReportByRole("All");
			}
		});
		mnReporting.add(mntmAllUsersList);
		mntmAllUsersList.setVisible(LoginPage.currentUser.getRoleUser().equals(
				"Admin"));

		JMenuItem mntmChildrensList = new JMenuItem("Children's list");
		mntmChildrensList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reportingService.generateUsersReportByRole("Child");
			}
		});
		mnReporting.add(mntmChildrensList);
		mntmChildrensList.setVisible(LoginPage.currentUser.getRoleUser()
				.equals("Admin")
				|| LoginPage.currentUser.getRoleUser().equals("Donor"));

		JMenuItem mntmDonorsList = new JMenuItem("Donor's list");
		mntmDonorsList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reportingService.generateUsersReportByRole("Donor");
			}
		});
		mnReporting.add(mntmDonorsList);
		mntmDonorsList.setVisible(LoginPage.currentUser.getRoleUser().equals(
				"Admin"));

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane
				.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

	}
}
