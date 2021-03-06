package tn.edu.espritCs.smile.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import tn.edu.espritCs.smile.domain.User;
import tn.edu.espritCs.smile.technical.UtilJdbc;

public class UserDao {

	private UtilJdbc utilJdbc = new UtilJdbc();

	public boolean addUser(User user) {
		boolean b = false;
		try {
			Statement statement = utilJdbc.GetConnetion().createStatement();
			String sql = "insert into user (firstNameUser,lastNameUser,roleUser,"
					+ "telUser,emailUser,loginUser,passwordUser) values('"
					+ user.getFirstNameUser()
					+ "','"
					+ user.getLastNameUser()
					+ "','"
					+ user.getRoleUser()
					+ "','"
					+ user.getTelUser()
					+ "','"
					+ user.getEmailUser()
					+ "','"
					+ user.getLoginUser()
					+ "','" + user.getPasswordUser() + "')";
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
				userTMP.setLoginUser(resultSet.getString("loginUser"));
				userTMP.setPasswordUser(resultSet.getString("passwordUser"));
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

	public boolean updateUserByField(User user, String field) {
		boolean b = false;
		try {
			if (field != "" && user != null) {
				Statement statement = utilJdbc.GetConnetion().createStatement();
				String sql = "";
				if (field == "firstNameUser") {
					sql = "update user set " + field + "='"
							+ user.getFirstNameUser() + "' where idUser="
							+ user.getIdUser();
				}
				if (field == "lastNameUser") {
					sql = "update user set " + field + "='"
							+ user.getLastNameUser() + "' where idUser="
							+ user.getIdUser();
				}
				if (field == "roleUser") {
					sql = "update user set " + field + "='"
							+ user.getRoleUser() + "' where idUser="
							+ user.getIdUser();
				}
				if (field == "telUser") {
					sql = "update user set " + field + "='" + user.getTelUser()
							+ "' where idUser=" + user.getIdUser();
				}
				if (field == "emailUser") {
					sql = "update user set " + field + "='"
							+ user.getEmailUser() + "' where idUser="
							+ user.getIdUser();
				}
				if (field == "loginUser") {
					sql = "update user set " + field + "='"
							+ user.getLoginUser() + "' where idUser="
							+ user.getIdUser();
				}
				if (field == "passwordUser") {
					sql = "update user set " + field + "='"
							+ user.getPasswordUser() + "' where idUser="
							+ user.getIdUser();
				}
				statement.executeUpdate(sql);
				b = true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return b;
	}

	public boolean updateUser(User user) {
		boolean b = false;
		try {
			if (user != null) {
				Statement statement = utilJdbc.GetConnetion().createStatement();
				String sql = "update user set firstNameUser='"
						+ user.getFirstNameUser() + "',lastNameUser='"
						+ user.getLastNameUser() + "',roleUser='"
						+ user.getRoleUser() + "',telUser='"
						+ user.getTelUser() + "',emailUser='"
						+ user.getEmailUser() + "',loginUser='"
						+ user.getLoginUser() + "',passwordUser='"
						+ user.getPasswordUser() + "' where idUser="
						+ user.getIdUser();
				statement.executeUpdate(sql);
				b = true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return b;
	}

	public ArrayList<User> getAllUsersByRole(String role) {
		ArrayList<User> lstUsers = new ArrayList<User>();
		try {
			Statement statement = utilJdbc.GetConnetion().createStatement();
			String sql = "select * from user";// Retrieve all users
			if (role != "All")// Filter retrieved users by role
				sql += " where roleUser='" + role + "'";
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				User userTMP = new User();
				userTMP.setIdUser(resultSet.getInt("idUser"));
				userTMP.setFirstNameUser(resultSet.getString("firstNameUser"));
				userTMP.setLastNameUser(resultSet.getString("lastNameUser"));
				userTMP.setRoleUser(resultSet.getString("roleUser"));
				userTMP.setTelUser(resultSet.getString("telUser"));
				userTMP.setEmailUser(resultSet.getString("emailUser"));
				userTMP.setLoginUser(resultSet.getString("loginUser"));
				userTMP.setPasswordUser(resultSet.getString("passwordUser"));
				lstUsers.add(userTMP);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstUsers;
	}

	public ArrayList<User> getAllUsersBySponsor(int idUserDonor) {
		ArrayList<User> lstUsers = new ArrayList<User>();
		try {
			Statement statement = utilJdbc.GetConnetion().createStatement();
			String sql = "select user.* from user inner join sponsorship on user.idUser=sponsorship.idUserChild where sponsorship.idUserDonor="
					+ idUserDonor;
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				User userTMP = new User();
				userTMP.setIdUser(resultSet.getInt("idUser"));
				userTMP.setFirstNameUser(resultSet.getString("firstNameUser"));
				userTMP.setLastNameUser(resultSet.getString("lastNameUser"));
				userTMP.setRoleUser(resultSet.getString("roleUser"));
				userTMP.setTelUser(resultSet.getString("telUser"));
				userTMP.setEmailUser(resultSet.getString("emailUser"));
				userTMP.setLoginUser(resultSet.getString("loginUser"));
				userTMP.setPasswordUser(resultSet.getString("passwordUser"));
				lstUsers.add(userTMP);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstUsers;
	}

	public String getAllAdminsEmailAdresses() {
		String emailAdresses = "";
		try {
			Statement statement = utilJdbc.GetConnetion().createStatement();
			String sql = "select emailUser from user where roleUser='Admin'";
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				if (emailAdresses != "")
					emailAdresses += ";";
				emailAdresses += resultSet.getString("emailUser");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emailAdresses;
	}
}
