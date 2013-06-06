package tn.edu.espritCs.smile.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tn.edu.espritCs.smile.domain.User;
import tn.edu.espritCs.smile.technical.UtilJdbc;

public class UserDao {

	private UtilJdbc utilJdbc = new UtilJdbc();

	public boolean addUser(User user) {
		boolean b = false;
		try {
			Statement statement = utilJdbc.GetConnetion().createStatement();
			String sql = "insert into user (firstNameUser,lastNameUser,roleUser,"
					+ "telUser,emailUser) values('"
					+ user.getFirstNameUser()
					+ "','"
					+ user.getLastNameUser()
					+ "','"
					+ user.getRoleUser()
					+ "','"
					+ user.getTelUser()
					+ "','"
					+ user.getEmailUser() + "')";
			statement.executeUpdate(sql);
			b = true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return b;
	}

	public User findUserById(int idUser) {
		User userTMP = new User();
		try {
			Statement statement = utilJdbc.GetConnetion().createStatement();
			String sql = "select * from user where idUser=" + idUser;
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				userTMP.setIdUser(resultSet.getInt("idUser"));
				userTMP.setFirstNameUser(resultSet.getString("firstNameUser"));
				userTMP.setLastNameUser(resultSet.getString("lastNameUser"));
				userTMP.setRoleUser(resultSet.getString("roleUser"));
				userTMP.setTelUser(resultSet.getString("telUser"));
				userTMP.setEmailUser(resultSet.getString("emailUser"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userTMP;
	}

	public boolean deleteUserById(int idUser) {
		boolean b = false;
		try {
			Statement statement = utilJdbc.GetConnetion().createStatement();
			String sql = "delete from user where idUser=" + idUser;
			statement.executeUpdate(sql);
			b = true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return b;
	}

	public boolean updateUser(User user, String field) {
		boolean b = false;
		try {
			if (field != "") {
				String value = "";
				if (field == "firstNameUser")
					value = user.getFirstNameUser();
				if (field == "lastNameUser")
					value = user.getLastNameUser();
				if (field == "roleUser")
					value = user.getRoleUser();
				if (field == "telUser")
					value = user.getTelUser();
				if (field == "emailUser")
					value = user.getEmailUser();

				Statement statement = utilJdbc.GetConnetion().createStatement();
				String sql = "update user set " + field + "='" + value
						+ "' where idUser=" + user.getIdUser();
				statement.executeUpdate(sql);
				b = true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return b;
	}

}
