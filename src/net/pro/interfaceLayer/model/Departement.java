package net.pro.interfaceLayer.model;

public class Departement {
	/*
	 * id_deprat : int
- nom:string
	 */

	private int id_deprat;
	private String nom;



	/**
	 * @param nom
	 */
	public Departement(String nom) {
		super();
		this.nom = nom;
	}
	/**
	 * @return the id_deprat
	 */
	public int getId_deprat() {
		return id_deprat;
	}
	/**
	 * @param id_deprat the id_deprat to set
	 */
	public void setId_deprat(int id_deprat) {
		this.id_deprat = id_deprat;
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

	public String toStringFull() {
		return "Departement [id_deprat=" + id_deprat + ", nom=" + nom + "]";
	}

	@Override
	public String toString() {
		return nom;
	}




}
