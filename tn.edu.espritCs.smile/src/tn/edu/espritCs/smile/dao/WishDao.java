package tn.edu.espritCs.smile.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tn.edu.espritCs.smile.domain.Wish;
import tn.edu.espritCs.smile.technical.UtilJdbc;

public class WishDao {

	private UtilJdbc utilJdbc = new UtilJdbc();

	public boolean addWish(Wish wish) {
		boolean b = false;
		try {
			Statement statement = utilJdbc.GetConnetion().createStatement();
			String sql = "insert into wish (descriptionWish,statusWish) values('"
					+ wish.getDescriptionWish()
					+ "','"
					+ wish.getStatusWish()
					+ "')";
			statement.executeUpdate(sql);
			b = true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return b;
	}

	public Wish findWishById(int idWish) {
		Wish wishTMP = new Wish();
		try {
			Statement statement = utilJdbc.GetConnetion().createStatement();
			String sql = "select * from wish where idWish=" + idWish;
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				wishTMP.setIdWish(resultSet.getInt("idWish"));
				wishTMP.setDescriptionWish(resultSet
						.getString("descriptionWish"));
				wishTMP.setStatusWish(resultSet.getString("statusWish"));
				wishTMP.setIdUserChild(resultSet.getInt("idUserChild"));
				wishTMP.setIdUserDonor(resultSet.getInt("idUserDonor"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return wishTMP;
	}

	public boolean deleteWishById(int idWish) {
		boolean b = false;
		try {
			Statement statement = utilJdbc.GetConnetion().createStatement();
			String sql = "delete from wish where idWish=" + idWish;
			statement.executeUpdate(sql);
			b = true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return b;
	}

	public boolean updateWish(Wish wish, String field) {
		boolean b = false;
		try {
			if (field != "") {
				Statement statement = utilJdbc.GetConnetion().createStatement();
				String sql = "";
				if (field == "descriptionWish") {
					sql = "update wish set " + field + "='"
							+ wish.getDescriptionWish() + "' where idWish="
							+ wish.getIdWish();
				}
				if (field == "statusWish") {
					sql = "update wish set " + field + "='"
							+ wish.getStatusWish() + "' where idWish="
							+ wish.getIdWish();
				}
				if (field == "idUserChild") {
					sql = "update wish set " + field + "="
							+ wish.getIdUserChild() + " where idWish="
							+ wish.getIdWish();
				}
				if (field == "idUserDonor") {
					sql = "update wish set " + field + "="
							+ wish.getIdUserDonor() + " where idWish="
							+ wish.getIdWish();
				}
				if (sql != "")
					statement.executeUpdate(sql);
				b = true;
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return b;
	}

}
