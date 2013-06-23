package tn.edu.espritCs.smile.services;

import java.sql.ResultSet;
import java.sql.Statement;

import tn.edu.espritCs.smile.dao.UserDao;
import tn.edu.espritCs.smile.domain.User;
import tn.edu.espritCs.smile.technical.UtilJdbc;

public class LoginService {

	public User login(String login, String password) {
		try {
			UtilJdbc connection = new UtilJdbc();
			Statement statement = connection.GetConnetion().createStatement();
			String sql = "select idUser from user where loginUser='" + login
					+ "' and passwordUser = '" + password + "'";
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				int idUser = resultSet.getInt("idUser");
				UserDao userDao = new UserDao();
				User user = userDao.findUserById(idUser);
				return user;
			}
			return null;
		} catch (Exception e) {
			return null;
		}
	}
}
