package tn.edu.espritCs.smile.technical;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UtilJdbc {

	public Connection connection;

	private String url = "jdbc:mysql://localhost:3306/smile";
	private String user = "root";
	private String password = "root";

	public Connection GetConnetion() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
}
