package net.pro.interfaceLayer.controller;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.pro.businessLayer.ImpIMetier;
import net.pro.businessLayer.Validator;
import net.pro.interfaceLayer.model.Departement;
import net.pro.interfaceLayer.model.Professeur;
import net.pro.utils.AlertMaker;

public class MainController implements Initializable {

	@FXML
	private JFXButton aboutBtn;

	@FXML
	private Label addDepartErrorsLabel;

	@FXML
	private AnchorPane addDepartErrorsPane;

	@FXML
	private TextField addDepartField;

	@FXML
	private TextField addProfAdresseField;

	@FXML
	private JFXButton addProfBtn;

	@FXML
	private TextField addProfCINField;

	@FXML
	private DatePicker addProfDateField;

	@FXML
	private ChoiceBox<Departement> addProfDeparField;

	@FXML
	private TextField addProfEmailField;

	@FXML
	private Label addProfErrorsLabel;

	@FXML
	private AnchorPane addProfErrorsPane;

	@FXML
	private TextField addProfNomField;

	@FXML
	private Pane addProfPane;

	@FXML
	private TextField addProfPrenomField;

	@FXML
	private TextField addProfTeleField;

	@FXML
	private TableView<Professeur> professeursTable;

	@FXML
	private TableColumn<Professeur, String> adresseTableCol;

	@FXML
	private TableColumn<Professeur, Date> dateTableCol;

	@FXML
	private TableColumn<Professeur, String> departementTableCol;

	@FXML
	private TableColumn<Professeur, String> emailTableCol;

	@FXML
	private TableColumn<Professeur, String> nomTableCol;

	@FXML
	private TableColumn<Professeur, String> prenomTableCol;

	@FXML
	private TableColumn<Professeur, String> teleTableCol;

	@FXML
	private TableColumn<Professeur, String> cinTableCol;

	@FXML
	private Label ajouterDepartLabel;

	@FXML
	private Button closeBtn;

	@FXML
	private MenuItem deleProfesseurMenuItem;

	@FXML
	private MenuItem deleteDepartContextMenu;

	@FXML
	private JFXButton departGestBtn;

	@FXML
	private ChoiceBox<Departement> departementFilterField;

	@FXML
	private MenuItem editDepartContextMenu;

	@FXML
	private TextField editProfAdresseField;

	@FXML
	private TextField editProfCINField;

	@FXML
	private DatePicker editProfDateField;

	@FXML
	private ChoiceBox<Departement> editProfDeparField;

	@FXML
	private TextField editProfEmailField;

	@FXML
	private TextField editProfNomField;

	@FXML
	private Pane editProfPane;

	@FXML
	private TextField editProfPrenomField;

	@FXML
	private TextField editProfTeleField;

	@FXML
	private MenuItem editProfesseurMenuItem;

	@FXML
	private AnchorPane errorsPaneEdit;

	@FXML
	private JFXButton filterBtn;

	@FXML
	private Pane gestionDepartPane;

	@FXML
	private Pane gestionProfPane;

	@FXML
	private Label labelPaneErrorsEdit;

	@FXML
	private ListView<Departement> listDepart;

	@FXML
	private JFXButton profGestBtn;

	@FXML
	private JFXButton saveDepartBtn;

	@FXML
	private TextField searchDepartField;

	@FXML
	private TextField searchProfField;


	//Constants
	private final String ERROR_CSS_CLASS = "error-on-pane" ;
	private final String SUCCESS_CSS_CLASS = "success-on-pane";

	//variables

	private ImpIMetier impIMetier;
	private Professeur edittedProfesseur;
	private Departement edittedDepart;



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		impIMetier = new ImpIMetier();
		initialiseTableViewCellsFactories();
		loadProfesseursToTableView(null, null);
		loadDepartementsToChoiceBoxesAndList();

	}

	// functions
	private void loadDepartementsToChoiceBoxesAndList() {
		List<Departement> departements = impIMetier.getAllDepartements();

		addProfDeparField.getItems().clear();
		addProfDeparField.getItems().addAll(departements);

		editProfDeparField.getItems().clear();
		editProfDeparField.getItems().addAll(departements);

		departementFilterField.getItems().clear();
		departementFilterField.getItems().addAll(departements);

		listDepart.getItems().clear();
		listDepart.getItems().addAll(departements);
	}
	private void initialiseTableViewCellsFactories() {
		//this.nomTCol = new TableColumn<Personne, String>("Nom");
		//this.nomTCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
		this.nomTableCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
		this.prenomTableCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		this.cinTableCol.setCellValueFactory(new PropertyValueFactory<>("cin"));
		this.adresseTableCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
		this.teleTableCol.setCellValueFactory(new PropertyValueFactory<>("telephone"));
		this.emailTableCol.setCellValueFactory(new PropertyValueFactory<>("email"));
		this.dateTableCol.setCellValueFactory(new PropertyValueFactory<>("date_recrutement"));
		this.departementTableCol.setCellValueFactory(new PropertyValueFactory<>("departement"));
	}

	private void loadProfesseursToTableView(Departement d, String keywords) {
		List<Professeur> professeurs;
		if(keywords == null) {
			if(d == null) {
				professeurs = impIMetier.getAllProfesseurs();
			}
			else {
				professeurs = impIMetier.getProfesseursDuDepartement(d.getId_deprat());
			}
		}else {
			professeurs = impIMetier.getProfesseursByKey(keywords);
		}
		//clear table
		professeursTable.getItems().clear();
		professeursTable.getItems().addAll(professeurs);
	}



	private void emptyProfesseurFields() {
		addProfNomField.setText("");
		addProfPrenomField.setText("");
		addProfCINField.setText("");
		addProfAdresseField.setText("");
		addProfEmailField.setText("");
		addProfTeleField.setText("");
		addProfDateField.setValue(LocalDate.now());
		//addProfErrorsLabel.setText("");

		editProfNomField.setText("");
		editProfPrenomField.setText("");
		editProfCINField.setText("");
		editProfAdresseField.setText("");
		editProfEmailField.setText("");
		editProfTeleField.setText("");
		editProfDateField.setValue(null);
		// errors label

	}

	//
	private Professeur extractProfessaurFromAddFields() {
		Professeur professeur = null;
		String nom = addProfNomField.getText().trim();
		String prenom = addProfPrenomField.getText().trim();
		String cin = addProfCINField.getText().trim();
		String adresse = addProfAdresseField.getText().trim();
		String tele = addProfTeleField.getText().trim();
		String email = addProfEmailField.getText().trim();
		LocalDate date_recrutement = addProfDateField.getValue();
		Date date_sql = date_recrutement != null ? Date.valueOf(date_recrutement) : null ;
		Departement depart = addProfDeparField.getValue();

		if(Validator.validateProfesseurData(nom, prenom, cin, adresse, tele, email, date_sql)) {
			professeur = new Professeur(nom, prenom, cin, adresse, tele, email, date_sql);
			if(depart != null) {
				professeur.setDepartement(depart);
			}
		}

		return professeur;
	}

	private Professeur extractProfessaurFromEditFields() {
		Professeur professeur = null;
		String nom = editProfNomField.getText().trim();
		String prenom = editProfPrenomField.getText().trim();
		String cin = editProfCINField.getText().trim();
		String adresse = editProfAdresseField.getText().trim();
		String tele = editProfTeleField.getText().trim();
		String email = editProfEmailField.getText().trim();
		LocalDate date_recrutement = editProfDateField.getValue();
		Date date_sql = date_recrutement != null ? Date.valueOf(date_recrutement) : null ;
		Departement depart = editProfDeparField.getValue();

		if(Validator.validateProfesseurTextData(nom, prenom, cin, adresse) &&
				Validator.validatePhoneNumber(tele) &&
				Validator.validateEmail(email) &&
				Validator.validateDate(date_sql)) {
			professeur = new Professeur(nom, prenom, cin, adresse, tele, email, date_sql);
			if(depart != null) {
				professeur.setDepartement(depart);
			}
		}

		return professeur;
	}


	// Gestion professeurs

	@FXML
	void addProfesseur(ActionEvent event) {
		addProfPane.toFront();
	}

	@FXML
	void editProfesseur(ActionEvent event) {
		Professeur professeurSelected = professeursTable.getSelectionModel().getSelectedItem();
		if(professeurSelected != null) {
			edittedProfesseur = professeurSelected;
			labelPaneErrorsEdit.setText("Element's id: "+professeurSelected.getId_prof());

			editProfNomField.setText(professeurSelected.getNom());
			editProfPrenomField.setText(professeurSelected.getPrenom());
			editProfCINField.setText(professeurSelected.getCin());
			editProfAdresseField.setText(professeurSelected.getAdresse());
			editProfTeleField.setText(professeurSelected.getTelephone());
			editProfEmailField.setText(professeurSelected.getEmail());
			editProfDateField.setValue(professeurSelected.getDate_recrutement().toLocalDate());
			if(professeurSelected.getDepartement() != null) {
				editProfDeparField.setValue(professeurSelected.getDepartement());
			}
			editProfPane.toFront();
		}
		else {
			AlertMaker.sendAlert(AlertType.ERROR, "Error", null, "No item selected!");
		}

	}

	@FXML
	void filterProfWithDepart(ActionEvent event) {
		Departement departementFilter = departementFilterField.getValue();
		loadProfesseursToTableView(departementFilter, null);
	}

	@FXML
	void saveAddingProfesseur(ActionEvent event) {
		Professeur professeur = extractProfessaurFromAddFields();
		if(professeur != null) {
			impIMetier.ajouterProfesseur(professeur);
			addProfErrorsLabel.setText("Professeur Added!");
			addProfErrorsPane.getStyleClass().add(SUCCESS_CSS_CLASS);

			loadProfesseursToTableView(null, null);
			emptyProfesseurFields();
		}
		else {
			addProfErrorsLabel.setText("Invalid data!");
			addProfErrorsPane.getStyleClass().add(ERROR_CSS_CLASS);
		}
	}

	@FXML
	void saveEditingProfesseur(ActionEvent event) {
		Professeur professeur = extractProfessaurFromEditFields();
		if(professeur != null) {
			impIMetier.modifierProfesseur(edittedProfesseur.getId_prof(), professeur);
			labelPaneErrorsEdit.setText("Professeur editted!");
			errorsPaneEdit.getStyleClass().add(SUCCESS_CSS_CLASS);

			loadProfesseursToTableView(null, null);
			emptyProfesseurFields();
		}
		else {
			labelPaneErrorsEdit.setText("Invalid data!");
			errorsPaneEdit.getStyleClass().add(ERROR_CSS_CLASS);
		}
	}

	@FXML
	void searchProfesseur(ActionEvent event) {
		String keyWords = searchProfField.getText();
		loadProfesseursToTableView(null, keyWords);
	}
	@FXML
	void cancelAddingProf(ActionEvent event) {
		loadProfesseursToTableView(null, null);
		emptyProfesseurFields();
		gestionProfPane.toFront();

	}

	@FXML
	void cancelEdittingProf(ActionEvent event) {
		loadProfesseursToTableView(null, null);
		emptyProfesseurFields();
		gestionProfPane.toFront();
	}
	@FXML
	void deleteProfesseur(ActionEvent event) {
		Professeur professeurSelected = professeursTable.getSelectionModel().getSelectedItem();
		if(professeurSelected != null) {
			Optional<ButtonType> choice = AlertMaker.sendAlert(AlertType.CONFIRMATION, 
					"Confirmation", "Delete Professeur",
					"Are you sure, want to delete professeur ? \n Info:"+professeurSelected.getNom()+" "+ professeurSelected.getPrenom());
			if(choice.get() == ButtonType.OK) {
				impIMetier.supprimerProfesseur(professeurSelected.getId_prof());
				loadProfesseursToTableView(null, null);
			}
		}
		else {
			AlertMaker.sendAlert(AlertType.ERROR, "Error", null, "No item selected!");
		}

	}


	// gestion departements

	@FXML
	void deleteDepartContext(ActionEvent event) {
		Departement departSelected = listDepart.getSelectionModel().getSelectedItem();
		if(departSelected != null) {
			Optional<ButtonType> choice = 
					AlertMaker.sendAlert(AlertType.CONFIRMATION, 
							"Confirmation", "Delete Departement", 
							"Are you sure, want to delete Departement ?\n Info: "+departSelected.toString());
			if(choice.get() == ButtonType.OK) {
				impIMetier.supprimerDepartement(departSelected.getId_deprat());
				loadDepartementsToChoiceBoxesAndList();
			}
		}
		else {
			AlertMaker.sendAlert(AlertType.ERROR, "Error", null, "No item selected!");
		}
	}



	@FXML
	void editDepartContext(ActionEvent event) {		
		Departement departSelected = listDepart.getSelectionModel().getSelectedItem();
		if(departSelected != null) {
			addDepartErrorsLabel.setText("Edit departement of id: "+ departSelected.getId_deprat());
			addDepartField.setText(departSelected.getNom());
			edittedDepart = departSelected;
		}
		else {
			AlertMaker.sendAlert(AlertType.ERROR, "Error", null, "No item selected!");
		}
	}

	@FXML
	void saveDepartement(ActionEvent event) {

		String departNom = addDepartField.getText().trim();
		boolean doDepartementNameExist = false;
		List<Departement> departements = impIMetier.getAllDepartements();

		for(Departement dep: departements) {
			if(departNom.equals(dep.getNom())) {
				doDepartementNameExist = true;
				break;
			}
		}

		if(departNom.isEmpty() || departNom.isBlank()) {
			addDepartErrorsLabel.setText("Please Enter a valid name.");
			addDepartErrorsPane.getStyleClass().add(ERROR_CSS_CLASS);
		}
		else if(doDepartementNameExist) {
			addDepartErrorsLabel.setText("Departement already exists.");
			addDepartErrorsPane.getStyleClass().add("error-on-pane");
		}
		else {
			Departement dep = new Departement(departNom);
			//addDepartErrorsLabel.setText("Edit departement of id: "+ departSelected.getId_deprat());
			if(edittedDepart != null) {
				impIMetier.modifierDepartement(edittedDepart.getId_deprat(), dep);
				addDepartErrorsLabel.setText("Departement updated.");
				edittedDepart = null;

			}
			else {
				impIMetier.ajouterDepartement(dep);
				addDepartErrorsLabel.setText("Departement added.");

			}

			addDepartErrorsPane.getStyleClass().add(SUCCESS_CSS_CLASS);//
			addDepartField.setText("");
			loadDepartementsToChoiceBoxesAndList();

		}
	}



	@FXML
	void searchDepartement(ActionEvent event) {
		String keyword = searchDepartField.getText().trim();
		List<Departement> departements = null;
		if(keyword.isEmpty() || keyword.isBlank()) {
			departements = impIMetier.getAllDepartements();
		}
		else {
			departements = impIMetier.getDepartementByNom(keyword);
		}

		listDepart.getItems().clear();
		listDepart.getItems().addAll(departements);

	}


	//open panes

	@FXML
	void openDepartGestion(ActionEvent event) {
		gestionDepartPane.toFront();
	}

	@FXML
	void openProfGestion(ActionEvent event) {
		gestionProfPane.toFront();

	}

	@FXML
	void openAboutPage(ActionEvent event) {
		AlertMaker.sendAlert(AlertType.INFORMATION, "About", null , "Made with love by Essadeq ELAAMIRI ");
	}

	@FXML
	void closeApp(ActionEvent event) {
		Stage stage = (Stage)this.closeBtn.getScene().getWindow();
		Optional<ButtonType> choice = AlertMaker.sendAlert(AlertType.CONFIRMATION, "Confirmation", "Close the application", "Are you sure, want to close the application ?");
		if(choice.get() == ButtonType.OK) {
			stage.close();
		}

	}



}




/*
 * 
 * 
 * 
 * 


	@FXML
	void closeApp(ActionEvent event) {

		// set 

	}

 */
