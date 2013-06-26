package tn.edu.espritCs.smile.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import tn.edu.espritCs.smile.domain.Wish;
import tn.edu.espritCs.smile.technical.UtilJdbc;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class MainMenu extends JFrame {

	private JPanel contentPane;

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

		JMenuItem mntmAskForA = new JMenuItem("Request a new wish");
		mntmAskForA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Wish wish = new Wish();
				RequestWishFrame requestWishFrame = new RequestWishFrame(wish);
				requestWishFrame.setVisible(true);
			}
		});
		mnViews.add(mntmAskForA);

		JMenuItem mntmManageWishes = new JMenuItem("View wishes");
		mntmManageWishes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListWishes listWishes = new ListWishes();
				listWishes.setVisible(true);
			}
		});

		JMenuItem mntmMyWishes = new JMenuItem("My wishes");
		mntmMyWishes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ListWishes listWishes = new ListWishes();
				listWishes.setVisible(true);
			}
		});
		mnViews.add(mntmMyWishes);
		mnViews.add(mntmManageWishes);

		JMenuItem mntmSponsorship = new JMenuItem("Sponsorship");
		mntmSponsorship.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SponsorshipForm sponsorshipForm = new SponsorshipForm();
				sponsorshipForm.setVisible(true);
			}
		});
		mnViews.add(mntmSponsorship);

		JMenu mnReporting = new JMenu("Reporting");
		menuBar.add(mnReporting);

		JMenuItem mntmAllUsersList = new JMenuItem("All user's list");
		mntmAllUsersList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				generateEMail("All");
			}
		});
		mnReporting.add(mntmAllUsersList);

		JMenuItem mntmChildrensList = new JMenuItem("Children's list");
		mntmChildrensList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateEMail("Child");
			}
		});
		mnReporting.add(mntmChildrensList);

		JMenuItem mntmDonorsList = new JMenuItem("Donor's list");
		mntmDonorsList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				generateEMail("Donor");
			}
		});
		mnReporting.add(mntmDonorsList);

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

	private void generateEMail(String role) {
		try {
			// - Connection to database
			UtilJdbc utilJdbc = new UtilJdbc();
			Connection connection = utilJdbc.GetConnetion();
			// - Loading and compilation of the report
			JasperDesign jasperDesign = JRXmlLoader
					.load("C:\\Users\\Safouane\\git\\Smile\\tn.edu.espritCs.smile\\reports\\usersList.jrxml");
			JasperReport jasperReport = JasperCompileManager
					.compileReport(jasperDesign);
			// - Parameters to send to report
			Map parameters = new HashMap();
			parameters.put("Role", role);
			// - Execution of the report
			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameters, connection);
			JasperViewer.viewReport(jasperPrint, false);
		} catch (JRException e) {
			System.out.println("Compilation error: " + e.getMessage());
			System.out.println(e.getStackTrace());
		}
	}
}
