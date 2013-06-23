package tn.edu.espritCs.smile.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import tn.edu.espritCs.smile.domain.User;
import tn.edu.espritCs.smile.services.LoginService;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldLogin;
	private JPasswordField passwordFieldPassword;
	private int tries = 0;
	public static User currentUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginPage() {

		setTitle("Identification form");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 315, 135);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane
				.setLayout(new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
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
						FormFactory.DEFAULT_ROWSPEC, }));

		JLabel lblLogin = new JLabel("Login:");
		contentPane.add(lblLogin, "4, 2, left, default");

		textFieldLogin = new JTextField();
		contentPane.add(textFieldLogin, "8, 2, fill, default");
		textFieldLogin.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		contentPane.add(lblPassword, "4, 4, left, default");

		passwordFieldPassword = new JPasswordField();
		contentPane.add(passwordFieldPassword, "8, 4, fill, default");

		JButton btnLogon = new JButton("Log me in");

		btnLogon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				login();
			}
		});
		contentPane.add(btnLogon, "8, 6");
	}

	private void login() {
		// Verification of Identification information
		String login = textFieldLogin.getText();
		StringBuilder sb = new StringBuilder();
		char[] pwd = passwordFieldPassword.getPassword();
		for (char c : pwd) {
			sb.append(c);
		}
		String password = sb.toString();
		LoginService loginService = new LoginService();
		User user = loginService.login(login, password);
		if (user != null) {
			LoginPage.currentUser = user;
			MainMenu mainMenuFrame = new MainMenu();
			mainMenuFrame.setVisible(true);
			dispose();
		} else {
			tries++;
			if (tries == 3)
				dispose();
		}
	}
}
