package tn.edu.espritCs.smile.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import tn.edu.espritCs.smile.dao.UserDao;
import tn.edu.espritCs.smile.domain.User;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class AccountFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldTel;
	private JTextField textFieldEmail;
	private JTextField textFieldLogin;
	private JPasswordField textFieldPassword;
	private JComboBox comboBoxRole;

	/**
	 * Create the frame.
	 */
	public AccountFrame(final User user) {
		setTitle("Account frame");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane
				.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"), }, new RowSpec[] {
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

		JLabel lblFirstName = new JLabel("First name:");
		contentPane.add(lblFirstName, "4, 2, left, default");

		textFieldFirstName = new JTextField();
		contentPane.add(textFieldFirstName, "6, 2, fill, default");
		textFieldFirstName.setColumns(10);

		JLabel lblLastName = new JLabel("Last name:");
		contentPane.add(lblLastName, "4, 4, left, default");

		textFieldLastName = new JTextField();
		contentPane.add(textFieldLastName, "6, 4, fill, default");
		textFieldLastName.setColumns(10);

		JLabel lblRole = new JLabel("R\u00F4le:");
		contentPane.add(lblRole, "4, 6, left, default");

		comboBoxRole = new JComboBox();
		comboBoxRole.addItem("Admin");
		comboBoxRole.addItem("Child");
		comboBoxRole.addItem("Donor");
		contentPane.add(comboBoxRole, "6, 6, fill, default");

		JLabel lblTel = new JLabel("Telephone:");
		contentPane.add(lblTel, "4, 8, left, default");

		textFieldTel = new JTextField();
		contentPane.add(textFieldTel, "6, 8, fill, default");
		textFieldTel.setColumns(10);

		JLabel lblEmail = new JLabel("E-mail:");
		contentPane.add(lblEmail, "4, 10, left, default");

		textFieldEmail = new JTextField();
		contentPane.add(textFieldEmail, "6, 10, fill, default");
		textFieldEmail.setColumns(10);

		JLabel lblLogin = new JLabel("Login:");
		contentPane.add(lblLogin, "4, 12, left, default");

		textFieldLogin = new JTextField();
		contentPane.add(textFieldLogin, "6, 12, fill, default");
		textFieldLogin.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		contentPane.add(lblPassword, "4, 14, left, default");

		textFieldPassword = new JPasswordField();
		contentPane.add(textFieldPassword, "6, 14, fill, default");
		textFieldPassword.setColumns(10);

		loadFields(user);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				char[] password = textFieldPassword.getPassword();
				StringBuilder sbPassword = new StringBuilder();
				for (char c : password)
					sbPassword.append(c);
				String roleUser = "";
				switch (comboBoxRole.getSelectedIndex()) {
				case 0:
					roleUser = "Admin";
					break;
				case 1:
					roleUser = "Child";
					break;
				case 2:
					roleUser = "Donor";
					break;
				default:
					break;
				}
				UserDao userDao = new UserDao();
				user.setFirstNameUser(textFieldFirstName.getText());
				user.setLastNameUser(textFieldLastName.getText());
				user.setRoleUser(roleUser);
				user.setTelUser(textFieldTel.getText());
				user.setEmailUser(textFieldEmail.getText());
				user.setLoginUser(textFieldLogin.getText());
				user.setPasswordUser(sbPassword.toString());
				if (user.getIdUser() == 0)
					userDao.addUser(user);
				else
					userDao.updateUser(user);
			}
		});
		contentPane.add(btnSave, "6, 16, left, default");
	}

	private void loadFields(User user) {
		try {
			if (user != null) {
				textFieldFirstName.setText(user.getFirstNameUser());
				textFieldLastName.setText(user.getLastNameUser());
				comboBoxRole.setSelectedItem(user.getRoleUser());
				textFieldTel.setText(user.getTelUser());
				textFieldEmail.setText(user.getEmailUser());
				textFieldLogin.setText(user.getLoginUser());
				textFieldPassword.setText(user.getPasswordUser());
			}
		} catch (Exception e) {

		}
	}
}
