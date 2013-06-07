package tn.edu.espritCs.smile.domain;

public class Wish {

	private int idWish;
	private String descriptionWish;
	private String statusWish;
	private int idUserChild;
	private int idUserDonor;

	public int getIdWish() {
		return idWish;
	}

	public void setIdWish(int idWish) {
		this.idWish = idWish;
	}

	public String getDescriptionWish() {
		return descriptionWish;
	}

	public void setDescriptionWish(String descriptionWish) {
		this.descriptionWish = descriptionWish;
	}

	public String getStatusWish() {
		return statusWish;
	}

	public void setStatusWish(String statusWish) {
		this.statusWish = statusWish;
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

	public Wish() {
		// TODO Auto-generated constructor stub
	}

	public Wish(String descriptionWish, String statusWish, int idUserChild,
			int idUserDonor) {
		super();
		this.descriptionWish = descriptionWish;
		this.statusWish = statusWish;
		this.idUserChild = idUserChild;
		this.idUserDonor = idUserDonor;
	}

}
