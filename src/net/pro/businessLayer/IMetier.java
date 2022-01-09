package net.pro.businessLayer;

import java.util.List;

import net.pro.interfaceLayer.model.Departement;
import net.pro.interfaceLayer.model.Professeur;

public interface IMetier {

	/*
	 * 	- Afficher la liste des professeurs
		- Rechercher des professeurs par mot Clé.
		- Ajouter un professeur
		- Supprimer un professeur
		- Modifier les informations d’un professeur
		- Affecter un professeur à un département
		2- Gérer les départements
		- Ajouter un département
		- Afficher la liste des départements
		- Supprimer un département
		- Modifier un département
		- Afficher la liste des professeurs d’un département
	 */

	public List<Professeur> getAllProfesseurs();
	public List<Professeur> getProfesseursByKey(String motCle);
	public boolean ajouterProfesseur(Professeur professeur);
	public boolean supprimerProfesseur(int id);
	public boolean modifierProfesseur(int id, Professeur nProfesseur);
	public boolean affecterProfesseurAuDepartement(int idProfesseur, int idDepartement);

	public List<Departement> getAllDepartements();
	public Departement getDepartementByID(int id);
	public List<Departement> getDepartementByNom(String keyWord);
	public List<Professeur> getProfesseursDuDepartement(int idDepartement);
	public boolean ajouterDepartement(Departement Departement);
	public boolean supprimerDepartement(int id);
	public boolean modifierDepartement(int id, Departement nDepartement);




}
