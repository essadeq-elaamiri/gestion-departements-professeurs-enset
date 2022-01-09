package net.pro.interfaceLayer.consoleApp;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import net.pro.businessLayer.ImpIMetier;
import net.pro.businessLayer.Validator;
import net.pro.interfaceLayer.model.Professeur;

public class GestionProfesseurs {

	private static ImpIMetier impMetier = new ImpIMetier();
	private static Scanner input = new Scanner(System.in);

	/*
	 * case 1:
				//AfficherListProfesseurs();
				break;
			case 2:
				//chercherProfesseurParMotCle();
				break;
			case 3:
				//AjouterProfesseur();
				break;
			case 4:
				//supprimerProfesseur();
				break;
			case 5:
				//modifierProfesseur();
				break;
			case 6:
				//AffecterProfesseur();
	 */

	public static void afficherListProfesseurs() {
		List<Professeur> professeurs = impMetier.getAllProfesseurs();
		if(professeurs == null) {
			System.out.println("la liste est vide"); 
			return;
		}
		for(Professeur professeur: professeurs) {
			System.out.println(professeur);
		}
	}

	public static void chercherProfesseurParMotCle() {
		//
		System.out.println("Entrer mot clé: ");
		String motCle = input.nextLine();
		System.out.println(motCle);
		List<Professeur> professeurs = impMetier.getProfesseursByKey(motCle);
		if(professeurs == null) {
			System.out.println("la liste est vide"); 
			return;
		}
		for(Professeur professeur: professeurs) {
			System.out.println(professeur);
		}

	}

	public static void ajouterProfesseur() {
		Professeur professeur = null;
		/*
		 * private String nom;
	private String prenom;
	private String cin;
	private String adresse;
	private String telephone;
	private String email;
	private Date date_recrutement;
		 */
		System.out.println("Nom: ");
		String nom = input.nextLine();

		System.out.println("Prenom: ");
		String prenom = input.nextLine();

		System.out.println("CIN: ");
		String cin = input.nextLine();

		System.out.println("Adresse: ");
		String adresse = input.nextLine();

		System.out.println("Telephone: ");
		String telephone = input.nextLine();

		System.out.println("Email: ");
		String email = input.nextLine();

		System.out.println("Date recrutement [2021-11-13]: ");
		String  date_s = input.nextLine();
		Date date_recrutement = Date.valueOf(date_s);

		if(Validator.validateProfesseurData(nom, prenom, cin, adresse, telephone, email, date_recrutement)) {
			professeur = new Professeur(nom, prenom, cin, adresse, telephone, email, date_recrutement);
			impMetier.ajouterProfesseur(professeur);
		}
		else {
			System.out.println("Erreur, les données entrées ne sont pas valides.");
		}

	}

	public static void supprimerProfesseur() {

	}

	public static void modifierProfesseur() {

	}
	public static void AffecterProfesseur() {

	}

}
