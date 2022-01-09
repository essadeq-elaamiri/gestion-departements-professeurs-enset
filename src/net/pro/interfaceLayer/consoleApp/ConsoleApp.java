package net.pro.interfaceLayer.consoleApp;

import java.io.IOException;
import java.util.Scanner;

public class ConsoleApp {

	private static final String PRINCIPAL_MENU = "=".repeat(40)+" MENU "+"=".repeat(40)+"\n1- Gestion des Professeurs."
			+ "\n2- Gestion des departements."
			+ "\n0- Quitter\n"
			+"=".repeat(86);

	private static final String PROFESSEURS_SUB_MENU = "=".repeat(40)+" GESTION PROFESSEURS "+"=".repeat(40)
			+"\n1- Afficher la liste des professeurs."
			+ "\n2- Rechercher des professeurs par mot Cl�"
			+ "\n3- Ajouter un professeur"
			+ "\n4- Supprimer un professeur"
			+ "\n5- Modifier les informations d�un professeur"
			+ "\n6- Affecter un professeur � un d�partement"
			+ "\n(-1)- Revenir � la list principale"
			+ "\n0- Quiter\n"
			+"=".repeat(80+" GESTION PROFESSEURS ".length());


	private static final String DEPARTEMENTS_SUB_MENU = "=".repeat(40)+" GESTION DEPARTEMENTS "+"=".repeat(40)
			+"\n1- Ajouter un d�partement"
			+ "\n2- Afficher la liste des d�partements"
			+ "\n3- Supprimer un d�partement"
			+ "\n4- Modifier un d�partement"
			+ "\n5- Afficher la liste des professeurs d�un d�partement "
			+ "\n(-1)- Revenir � la list principale"
			+ "\n0- Quiter\n"
			+"=".repeat(80+" GESTION DEPARTEMENTS ".length());

	private static Scanner input = new Scanner(System.in);

	public static void startApp() {
		//afficher Menu
		int choix_principal = 0;
		do {
			System.out.println(PRINCIPAL_MENU);
			choix_principal = demanderChoix();
			if(choix_principal == 1) {
				gestionProfesseurs();
				break;
			}
			else if(choix_principal == 2) {
				gestionDepartements();
				break;
			}
			else if(choix_principal != 0) {
				System.out.println("Les choix possibles [1, 2, 0].");
				break;
			}

		}
		while(choix_principal != 0);
		System.out.println("Vous avez quitt�.");

	}

	private static int demanderChoix() {
		System.out.println("\nEntrer choix: ");
		return input.nextInt();
	}

	private static void gestionProfesseurs() {

		/*
		 * - Afficher la liste des professeurs
			- Rechercher des professeurs par mot Cl�
			- Ajouter un professeur
			- Supprimer un professeur
			- Modifier les informations d�un professeur
			- Affecter un professeur � un d�partement

		 */
		int choix_principal = 0;
		do {
			System.out.println(PROFESSEURS_SUB_MENU);
			choix_principal = demanderChoix();
			switch(choix_principal){
			case 1:
				GestionProfesseurs.afficherListProfesseurs();
				break;
			case 2:
				GestionProfesseurs.chercherProfesseurParMotCle();
				break;
			case 3:
				GestionProfesseurs.ajouterProfesseur();
				break;
			case 4:
				//supprimerProfesseur();
				break;
			case 5:
				//modifierProfesseur();
				break;
			case 6:
				//AffecterProfesseur();
				break;
			case -1:
				startApp();
				break;
			default:
				if(choix_principal != 0)
					System.out.println("Les choix possibles [1, 2, 3, 4, 5, 6, -1, 0].");
				break;


			}

		}
		while(choix_principal != 0);
		System.out.println("Vous avez quitt�.");
	}

	private static void gestionDepartements() {
		int choix_principal = 0;
		/*
		 * - Ajouter un d�partement
			- Afficher la liste des d�partements
			- Supprimer un d�partement
			- Modifier un d�partement
			- Afficher la liste des professeurs d�un d�partement
		 */
		do {
			System.out.println(DEPARTEMENTS_SUB_MENU);
			choix_principal = demanderChoix();
			switch(choix_principal){
			case 1:
				//ajouterDepartement();
				break;
			case 2:
				//afficherListDepartements();
				break;
			case 3:
				//supprimerDepartement();
				break;
			case 4:
				//modifierDepartement();
				break;
			case 5:
				//afficherListProfesseursDuDepartement();
				break;
			case -1:
				startApp();
				break;
			default:
				if(choix_principal != 0)
					System.out.println("Les choix possibles [1, 2, 3, 4, 5, 6, -1, 0].");
				break;


			}

		}
		while(choix_principal != 0);
		System.out.println("Vous avez quitt�.");
	}

	private static void clearConsole() {
		try {
			Runtime.getRuntime().exec("cls");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
