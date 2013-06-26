package tn.edu.espritCs.smile.domain;

public class User {

	private int idUser;
	private String firstNameUser;
	private String lastNameUser;
	private String roleUser;
	private String telUser;
	private String emailUser;
	private String loginUser;
	private String passwordUser;

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getFirstNameUser() {
		return firstNameUser;
	}

	public void setFirstNameUser(String firstNameUser) {
		this.firstNameUser = firstNameUser;
	}

	public String getLastNameUser() {
		return lastNameUser;
	}

	public void setRoleUser(String roleUser) {
		this.roleUser = roleUser;
	}

	public String getRoleUser() {
		return roleUser;
	}

	public void setLastNameUser(String lastNameUser) {
		this.lastNameUser = lastNameUser;
	}

	public String getTelUser() {
		return telUser;
	}

	public void setTelUser(String telUser) {
		this.telUser = telUser;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}

	public String getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}

	public String getPasswordUser() {
		return passwordUser;
	}

	public void setPasswordUser(String passwordUser) {
		this.passwordUser = passwordUser;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String firstNameUser, String lastNameUser, String roleUser,
			String telUser, String emailUser, String loginUser,
			String passwordUser) {
		super();
		this.firstNameUser = firstNameUser;
		this.lastNameUser = lastNameUser;
		this.roleUser = roleUser;
		this.telUser = telUser;
		this.emailUser = emailUser;
		this.loginUser = loginUser;
		this.passwordUser = passwordUser;
	}

	@Override
	public boolean equals(Object arg0) {
		return this.getIdUser() == ((User) arg0).getIdUser();
	}
}
