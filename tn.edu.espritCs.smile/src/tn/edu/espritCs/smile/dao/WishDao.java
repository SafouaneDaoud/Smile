package tn.edu.espritCs.smile.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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

	public boolean updateWish(Wish wish) {
		boolean b = false;
		try {
			Statement statement = utilJdbc.GetConnetion().createStatement();
			String sql = "update wish set descriptionWish='"
					+ wish.getDescriptionWish() + "',statusWish='"
					+ wish.getStatusWish() + "'";
			if (wish.getIdUserChild() > 0)
				sql += ",idUserChild=" + wish.getIdUserChild();
			if (wish.getIdUserDonor() > 0)
				sql += ",idUserDonor=" + wish.getIdUserDonor();
			sql += " where idWish=" + wish.getIdWish();
			if (sql != "")
				statement.executeUpdate(sql);
			b = true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return b;
	}

	public ArrayList<Wish> getAllWishes() {
		ArrayList<Wish> lstWishes = new ArrayList<Wish>();
		try {
			Statement statement = utilJdbc.GetConnetion().createStatement();
			String sql = "select * from wish";
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Wish wishTMP = new Wish();
				wishTMP.setIdWish(resultSet.getInt("idWish"));
				wishTMP.setDescriptionWish(resultSet
						.getString("descriptionWish"));
				wishTMP.setStatusWish(resultSet.getString("statusWish"));
				wishTMP.setIdUserChild(resultSet.getInt("idUserChild"));
				wishTMP.setIdUserDonor(resultSet.getInt("idUserDonor"));
				lstWishes.add(wishTMP);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstWishes;
	}

	public ArrayList<Wish> getAllChildWishes(int idUser) {
		ArrayList<Wish> lstWishes = new ArrayList<Wish>();
		try {
			Statement statement = utilJdbc.GetConnetion().createStatement();
			String sql = "select * from wish where idUserChild=" + idUser;
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Wish wishTMP = new Wish();
				wishTMP.setIdWish(resultSet.getInt("idWish"));
				wishTMP.setDescriptionWish(resultSet
						.getString("descriptionWish"));
				wishTMP.setStatusWish(resultSet.getString("statusWish"));
				wishTMP.setIdUserChild(resultSet.getInt("idUserChild"));
				wishTMP.setIdUserDonor(resultSet.getInt("idUserDonor"));
				lstWishes.add(wishTMP);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstWishes;
	}

	public ArrayList<Wish> getAllWishesByStatus(String status) {
		ArrayList<Wish> lstWishes = new ArrayList<Wish>();
		try {
			Statement statement = utilJdbc.GetConnetion().createStatement();
			String sql = "select * from wish where statusWish=" + status + "'";
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Wish wishTMP = new Wish();
				wishTMP.setIdWish(resultSet.getInt("idWish"));
				wishTMP.setDescriptionWish(resultSet
						.getString("descriptionWish"));
				wishTMP.setStatusWish(resultSet.getString("statusWish"));
				wishTMP.setIdUserChild(resultSet.getInt("idUserChild"));
				wishTMP.setIdUserDonor(resultSet.getInt("idUserDonor"));
				lstWishes.add(wishTMP);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lstWishes;
	}
}
