package tn.edu.espritCs.smile.domain;

public class Sponsorship {

	private int idSponsorship;
	private int idUserChild;
	private int idUserDonor;

	public int getIdSponsorship() {
		return idSponsorship;
	}

	public void setIdSponsorship(int idSponsorship) {
		this.idSponsorship = idSponsorship;
	}

	public int getIdUserChild() {
		return idUserChild;
	}

	public void setIdUserChild(int idUserChild) {
		this.idUserChild = idUserChild;
	}

	public int getIdUserDonor() {
		return idUserDonor;
	}

	public void setIdUserDonor(int idUserDonor) {
		this.idUserDonor = idUserDonor;
	}

	public Sponsorship() {
		// TODO Auto-generated constructor stub
	}

	public Sponsorship(int idUserChild, int idUserDonor) {
		super();
		this.idUserChild = idUserChild;
		this.idUserDonor = idUserDonor;
	}

}
