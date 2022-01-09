package net.pro.interfaceLayer.controller;
import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class GestionProfesseurController{ //net.pro.interfaceLayer.controller.GestionProfesseurController

	@FXML
	private JFXButton addProfBtn;

	@FXML
	private TableColumn<?, ?> adresseTableCol;

	@FXML
	private TableColumn<?, ?> cinTableCol;

	@FXML
	private TableColumn<?, ?> dateTableCol;

	@FXML
	private MenuItem deleProfesseurMenuItem;

	@FXML
	private ChoiceBox<?> departementFilterField;

	@FXML
	private TableColumn<?, ?> departementTableCol;

	@FXML
	private MenuItem editProfesseurMenuItem;

	@FXML
	private TableColumn<?, ?> emailTableCol;

	@FXML
	private JFXButton filterBtn;

	@FXML
	private TableColumn<?, ?> nomTableCol;

	@FXML
	private TableColumn<?, ?> prenomTableCol;

	@FXML
	private TableView<?> professeursTable;

	@FXML
	private TextField searchProfField;

	@FXML
	private TableColumn<?, ?> teleTableCol;

	@FXML
	void ajouterProfesseurs(ActionEvent event) {

	}

	@FXML
	void deleteProfesseur(ActionEvent event) {

	}

	@FXML
	void editProfesseur(ActionEvent event) {

	}

	@FXML
	void filterProfWithDepart(ActionEvent event) {

	}

	@FXML
	void searchProfesseur(ActionEvent event) {

	}

}
