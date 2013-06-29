package tn.edu.espritCs.smile.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tn.edu.espritCs.smile.dao.UserDao;
import tn.edu.espritCs.smile.domain.User;
import tn.edu.espritCs.smile.services.ReportingService;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class ListUsers extends JFrame {

	private JPanel contentPane;
	private JList listBoxUsers;
	private JComboBox comboBoxRole;
	private JLabel lblRole;
	private JButton btnDeleteUser;
	private JButton btnModifyUser;
	private JButton btnAddUser;
	private JButton btnPrint;

	private Integer currentIdUser;
	private String[] listUserNames;
	private int[] listUserIds;

	private ReportingService reportingService = new ReportingService();
	private JButton btnRefresh;

	/**
	 * Create the frame.
	 */
	public ListUsers() {
		setTitle("List of users");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 564, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FormLayout(
				new ColumnSpec[] { FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		lblRole = new JLabel("Role:");
		contentPane.add(lblRole, "2, 2, right, default");

		listBoxUsers = new JList();
		loadListUsers("All");
		listBoxUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listBoxUsers.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (listUserIds.length > 0
						&& listUserIds.length > listBoxUsers.getSelectedIndex())
					currentIdUser = listUserIds[listBoxUsers.getSelectedIndex()];
				else
					currentIdUser = 0;
			}
		});
		contentPane.add(listBoxUsers, "2, 4, 9, 17, fill, fill");

		comboBoxRole = new JComboBox();
		comboBoxRole.addItem("All");
		comboBoxRole.addItem("Admin");
		comboBoxRole.addItem("Child");
		comboBoxRole.addItem("Donor");
		comboBoxRole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String roleUser = "";
				switch (comboBoxRole.getSelectedIndex()) {
				case 0:
					roleUser = "All";
					break;
				case 1:
					roleUser = "Admin";
					break;
				case 2:
					roleUser = "Child";
					break;
				case 3:
					roleUser = "Donor";
					break;
				default:
					break;
				}
				loadListUsers(roleUser);
			}
		});
		contentPane.add(comboBoxRole, "4, 2, fill, default");

		btnAddUser = new JButton("Add user");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				User user = new User();
				if (user != null) {
					AccountFrame accountFrame = new AccountFrame(user);
					accountFrame.setVisible(true);
				}
			}
		});
		contentPane.add(btnAddUser, "12, 4");

		btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				// - Connection to database
				// UtilJdbc utilJdbc = new UtilJdbc();
				// Connection connection = utilJdbc.GetConnetion();
				// // - Loading and compilation of the report
				// JasperDesign jasperDesign = JRXmlLoader
				// .load("C:\\Users\\Safouane\\git\\Smile\\tn.edu.espritCs.smile\\reports\\usersList.jrxml");
				// JasperReport jasperReport = JasperCompileManager
				// .compileReport(jasperDesign);
				// // - Parameters to send to report
				// Map parameters = new HashMap();
				String roleUser = "";
				switch (comboBoxRole.getSelectedIndex()) {
				case 0:
					roleUser = "All";
					break;
				case 1:
					roleUser = "Admin";
					break;
				case 2:
					roleUser = "Child";
					break;
				case 3:
					roleUser = "Donor";
					break;
				default:
					break;
				}
				reportingService.generateUsersReportByRole(roleUser);

				// parameters.put("Role", roleUser);
				// // - Execution of the report
				// JasperPrint jasperPrint = JasperFillManager.fillReport(
				// jasperReport, parameters, connection);
				// // - Show report in Viewer
				// JasperViewer.viewReport(jasperPrint, false);
				// // - Creation of report to PDF format
				// // JasperExportManager
				// // .exportReportToPdfFile(
				// // jasperPrint,
				// //
				// "C:\\Users\\Safouane\\git\\Smile\\tn.edu.espritCs.smile\\reports\\usersList.pdf");
				// // System.out.println("success");
			}
		});

		btnDeleteUser = new JButton("Delete user");
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserDao userDao = new UserDao();
				if (currentIdUser != 0)
					userDao.deleteUserById(currentIdUser);
			}
		});

		btnModifyUser = new JButton("Modify user");
		btnModifyUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserDao userDao = new UserDao();
				User user = userDao.findUserById(currentIdUser);
				if (user != null && currentIdUser != 0) {
					AccountFrame accountFrame = new AccountFrame(user);
					accountFrame.setVisible(true);
				}
			}
		});
		contentPane.add(btnModifyUser, "12, 6");
		contentPane.add(btnDeleteUser, "12, 8");

		btnRefresh = new JButton("Refresh");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String roleUser = "";
				switch (comboBoxRole.getSelectedIndex()) {
				case 0:
					roleUser = "All";
					break;
				case 1:
					roleUser = "Admin";
					break;
				case 2:
					roleUser = "Child";
					break;
				case 3:
					roleUser = "Donor";
					break;
				default:
					break;
				}
				loadListUsers(roleUser);
			}
		});
		contentPane.add(btnRefresh, "12, 10");
		contentPane.add(btnPrint, "12, 12");
	}

	private void loadListUsers(String role) {
		try {
			UserDao userDao = new UserDao();
			ArrayList<User> lstUsers = userDao.getAllUsersByRole(role);
			listUserNames = new String[lstUsers.size()];
			listUserIds = new int[lstUsers.size()];
			for (int i = 0; i < lstUsers.size(); i++) {
				User user = lstUsers.get(i);

				String dataUser = user.getRoleUser() + ": "
						+ user.getFirstNameUser() + " "
						+ user.getLastNameUser() + " -Telephone: "
						+ user.getTelUser() + " / EMail: "
						+ user.getEmailUser();
				listUserNames[i] = dataUser;
				listUserIds[i] = user.getIdUser();
			}
			listBoxUsers.setSelectedIndex(0);
			listBoxUsers.setListData(listUserNames);
		} catch (Exception e) {
		}
	}
}
