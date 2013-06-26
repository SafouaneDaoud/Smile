package tn.edu.espritCs.smile.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tn.edu.espritCs.smile.domain.Sponsorship;
import tn.edu.espritCs.smile.technical.UtilJdbc;

public class SponsorshipDao {
	private UtilJdbc utilJdbc = new UtilJdbc();

	public boolean addSponsorship(Sponsorship sponsorship) {
		boolean b = false;
		try {
			Statement statement = utilJdbc.GetConnetion().createStatement();
			String sql = "insert into sponsorship (idUserChild,idUserDonor) values("
					+ sponsorship.getIdUserChild()
					+ ","
					+ sponsorship.getIdUserDonor() + ")";
			statement.executeUpdate(sql);
			b = true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return b;
	}

	public Sponsorship findSponsorshipById(int idSponsorship) {
		Sponsorship sponsorshipTMP = new Sponsorship();
		try {
			Statement statement = utilJdbc.GetConnetion().createStatement();
			String sql = "select * from sponsorship where idSponsorship="
					+ idSponsorship;
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				sponsorshipTMP.setIdSponsorship(resultSet
						.getInt("idSponsorship"));
				sponsorshipTMP.setIdUserChild(resultSet.getInt("idUserChild"));
				sponsorshipTMP.setIdUserDonor(resultSet.getInt("idUserDonor"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sponsorshipTMP;
	}

	public Sponsorship findSponsorshipByDonorChildIds(int idDonor, int idChild) {
		Sponsorship sponsorshipTMP = null;
		try {
			Statement statement = utilJdbc.GetConnetion().createStatement();
			String sql = "select * from sponsorship where idUserDonor = "
					+ idDonor + " and idUserChild = " + idChild;
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				sponsorshipTMP = new Sponsorship();
				sponsorshipTMP.setIdSponsorship(resultSet
						.getInt("idSponsorship"));
				sponsorshipTMP.setIdUserChild(resultSet.getInt("idUserChild"));
				sponsorshipTMP.setIdUserDonor(resultSet.getInt("idUserDonor"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sponsorshipTMP;
	}

	public boolean deleteSponsorshipById(int idSponsorship) {
		boolean b = false;
		try {
			Statement statement = utilJdbc.GetConnetion().createStatement();
			String sql = "delete from sponsorship where idSponsorship="
					+ idSponsorship;
			statement.executeUpdate(sql);
			b = true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return b;
	}

	public boolean updateSponsorship(Sponsorship sponsorship) {
		boolean b = false;
		try {
			Statement statement = utilJdbc.GetConnetion().createStatement();
			String sql = "update sponsorship set idUserChild="
					+ sponsorship.getIdUserChild() + ",idUserDonor="
					+ sponsorship.getIdUserDonor() + " where idSponsorship="
					+ sponsorship.getIdSponsorship();

			statement.executeUpdate(sql);
			b = true;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return b;
	}

}
