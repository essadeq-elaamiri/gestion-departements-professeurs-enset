package net.pro.businessLayer;

import java.sql.Date;
import java.time.LocalDate;

public class Validator {

	private static final LocalDate ENSET_STARTS_AT = LocalDate.of(1988, 12, 12);

	public static boolean validateText(String text){
		if(text.isEmpty() || text.isBlank()) return false;
		else return true;
	}
	public static boolean validateEmail(String email){
		if(!validateText(email)) return false;
		/*
		 * il faut que l'email s'ecrit comme essadeq0701@gmail.com, il doit commencer par des lettres
		 */
		if(!email.matches("^([a-zA-Z]+[a-zA-Z1-9\\\\_\\\\-]*)@gmail.com$")) return false;
		else return true;
	}
	public static boolean validatePhoneNumber(String phone){
		if(!validateText(phone)) return false;

		if(!phone.matches("^0(6|7)\\d{8}$")) return false;
		else return true;

	}
	public static boolean validateDate(Date ld){
		if(ld == null) return false;
		if(ld.before(Date.valueOf(ENSET_STARTS_AT))) return false;
		return true;


	}

	public static boolean validateProfesseurData(String nom, String prenom, String cin, String adresse, String telephone, String email,
			Date date_recrutement) {

		if(!(validateText(nom) && validateText(prenom) && validateText(cin) && validateEmail(email) && validateDate(date_recrutement))) {
			return false;
		}
		return true;
	}

	public static boolean validateProfesseurTextData(String ...textData) {

		for(String txt : textData) {
			if(!validateText(txt)) return false;
		}
		return true;
	}

}
