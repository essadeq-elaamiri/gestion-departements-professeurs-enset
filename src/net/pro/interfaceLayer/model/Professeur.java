package net.pro.interfaceLayer.model;

import java.sql.Date;

public class Professeur {
	/*
	 * id_prof : int
- nom :string
- prenom :string
- cin :string
- adresse :string
- telephone :string
- email :string
- date_recrutement :date
	 */

	private int id_prof;
	private String nom;
	private String prenom;
	private String cin;
	private String adresse;
	private String telephone;
	private String email;
	private Date date_recrutement;
	private Departement departement;
	/**
	 * @param nom
	 * @param prenom
	 * @param cin
	 * @param adresse
	 * @param telephone
	 * @param email
	 * @param date_recrutement
	 * @param departement
	 */
	public Professeur(String nom, String prenom, String cin, String adresse, String telephone, String email,
			Date date_recrutement) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.cin = cin;
		this.adresse = adresse;
		this.telephone = telephone;
		this.email = email;
		this.date_recrutement = date_recrutement;

	}
	/**
	 * @return the id_prof
	 */
	public int getId_prof() {
		return id_prof;
	}
	/**
	 * @param id_prof the id_prof to set
	 */
	public void setId_prof(int id_prof) {
		this.id_prof = id_prof;
	}
	/**
	 * @return the nom
	 */
	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return the cin
	 */
	public String getCin() {
		return cin;
	}
	/**
	 * @param cin the cin to set
	 */
	public void setCin(String cin) {
		this.cin = cin;
	}
	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return adresse;
	}
	/**
	 * @param adresse the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the date_recrutement
	 */
	public Date getDate_recrutement() {
		return date_recrutement;
	}
	/**
	 * @param date_recrutement the date_recrutement to set
	 */
	public void setDate_recrutement(Date date_recrutement) {
		this.date_recrutement = date_recrutement;
	}
	/**
	 * @return the departement
	 */
	public Departement getDepartement() {
		return departement;
	}
	/**
	 * @param departement the departement to set
	 */
	public void setDepartement(Departement departement) {
		this.departement = departement;
	}
	@Override
	public String toString() {
		String depar = departement == null ? "[NO SET]" : departement.toString();
		return "Professeur [id_prof=" + id_prof + ", nom=" + nom + ", prenom=" + prenom + ", cin=" + cin + ", adresse="
		+ adresse + ", telephone=" + telephone + ", email=" + email + ", date_recrutement=" + date_recrutement
		+ ", departement=" + depar + "]";
	}







}
