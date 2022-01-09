package net.pro.businessLayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import javafx.scene.control.Alert.AlertType;
import net.pro.databaseLayer.SignletonConnexionDB;
import net.pro.interfaceLayer.model.Departement;
import net.pro.interfaceLayer.model.Professeur;
import net.pro.utils.AlertMaker;

public class ImpIMetier implements IMetier {
	Connection connection = null;

	//queries
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

	private final String TABLE_PROFESSEUR = "professeur";
	private final String TABLE_DEPARTEMENT = "departement";

	private final String [] COL_NAMES_PROFESSEUR =  
		{"id_prof","nom","prenom","cin","adresse","telephone", "email", "date_recrutement", "id_depart"}; //9
	private final String [] COL_NAMES_DEPARTEMANT =  {"id_depart","nom"};

	private final String GET_ALL = "SELECT * FROM {{TABLE_NAME}};";
	private final String GET_ALL_LIKE = "SELECT * FROM {{TABLE_NAME}} WHERE {{COL_NAME}} LIKE ?";
	private final String GET_ONE_BY_COL = "SELECT * FROM {{TABLE_NAME}} WHERE {{COL_NAME}}=?;";

	private final String INSERT_PROFESSEUR = "INSERT INTO "+TABLE_PROFESSEUR+" "
			+ "("+COL_NAMES_PROFESSEUR[1]+
			","+COL_NAMES_PROFESSEUR[2]+
			","+COL_NAMES_PROFESSEUR[3]+
			","+COL_NAMES_PROFESSEUR[4]+
			","+COL_NAMES_PROFESSEUR[5]+
			","+COL_NAMES_PROFESSEUR[6]+
			","+COL_NAMES_PROFESSEUR[7]+
			", "+COL_NAMES_PROFESSEUR[8]+") VALUES (?,?,?,?,?,?,?,?);";
	private final String INSERT_DEPARTEMENT = "INSERT INTO "+TABLE_DEPARTEMENT+" ("+COL_NAMES_DEPARTEMANT[1]+") VALUES (?);";

	private final String UPDATE_PROFESSEUR = "UPDATE "+TABLE_PROFESSEUR+" SET "
			+COL_NAMES_PROFESSEUR[1]+"=?, "
			+COL_NAMES_PROFESSEUR[2]+"=?, "
			+COL_NAMES_PROFESSEUR[3]+"=?, "
			+COL_NAMES_PROFESSEUR[4]+"=?, "
			+COL_NAMES_PROFESSEUR[5]+"=?, "
			+COL_NAMES_PROFESSEUR[6]+"=?, "
			+COL_NAMES_PROFESSEUR[7]+"=?, "
			+COL_NAMES_PROFESSEUR[8]+"=? "+
			" WHERE "+COL_NAMES_PROFESSEUR[0]+"=?;";

	private final String UPDATE_DEPARTEMENT = "UPDATE "+TABLE_DEPARTEMENT+" SET "+COL_NAMES_DEPARTEMANT[1]+"=? WHERE "+COL_NAMES_DEPARTEMANT[0]+"=?;";

	private final String DELETE = "DELETE FROM {{TABLE_NAME}} WHERE {{COL_NAME}}=? ";
	private final String DELETE_ALL = "DELETE FROM {{TABLE_NAME}} WHERE 1=1";

	private final String SET_PROF_DEP = "UPDATE "+TABLE_PROFESSEUR+" SET "+COL_NAMES_PROFESSEUR[8]+"=? WHERE  "+COL_NAMES_PROFESSEUR[8]+"=?";




	/**
	 * @param connection
	 */
	public ImpIMetier() {

		this.connection = SignletonConnexionDB.getConnexion();
	}



	@Override
	public List<Professeur> getAllProfesseurs() {
		String query = GET_ALL.replace("{{TABLE_NAME}}", TABLE_PROFESSEUR);
		Statement statement = null; 
		ResultSet resultSet = null;
		List<Professeur> professeurs = new ArrayList<Professeur>(); 
		Professeur professeur = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				professeur = new Professeur(
						resultSet.getString(COL_NAMES_PROFESSEUR[1]),
						resultSet.getString(COL_NAMES_PROFESSEUR[2]),
						resultSet.getString(COL_NAMES_PROFESSEUR[3]),
						resultSet.getString(COL_NAMES_PROFESSEUR[4]),
						resultSet.getString(COL_NAMES_PROFESSEUR[5]),
						resultSet.getString(COL_NAMES_PROFESSEUR[6]),
						resultSet.getDate(COL_NAMES_PROFESSEUR[7]));
				professeur.setId_prof(resultSet.getInt(COL_NAMES_PROFESSEUR[0]));
				//retrive departement
				//id 
				int dep_id = resultSet.getInt(COL_NAMES_PROFESSEUR[8]);
				Departement departement = getDepartementByID(dep_id);
				if(departement != null) professeur.setDepartement(departement);
				professeurs.add(professeur);
			}
		} catch (SQLException e) {
			sendErrorWindow(e);
		}
		finally {
			try {
				statement.close();
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return professeurs;
	}

	@Override
	public List<Professeur> getProfesseursByKey(String motCle) {
		String query = GET_ALL_LIKE.replace("{{TABLE_NAME}}", TABLE_PROFESSEUR).replace("{{COL_NAME}}", COL_NAMES_PROFESSEUR[1]);
		List<Professeur> professeurs = new ArrayList<>();
		Professeur professeur;
		PreparedStatement preparedStatement = null; 
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%"+motCle+"%");
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				professeur = new Professeur(
						resultSet.getString(COL_NAMES_PROFESSEUR[1]),
						resultSet.getString(COL_NAMES_PROFESSEUR[2]),
						resultSet.getString(COL_NAMES_PROFESSEUR[3]),
						resultSet.getString(COL_NAMES_PROFESSEUR[4]),
						resultSet.getString(COL_NAMES_PROFESSEUR[5]),
						resultSet.getString(COL_NAMES_PROFESSEUR[6]),
						resultSet.getDate(COL_NAMES_PROFESSEUR[7]));
				professeur.setId_prof(resultSet.getInt(COL_NAMES_PROFESSEUR[0]));
				//retrive departement
				//id 
				int dep_id = resultSet.getInt(COL_NAMES_PROFESSEUR[8]);
				Departement departement = getDepartementByID(dep_id);
				professeur.setDepartement(departement);
				professeurs.add(professeur);
			}

		} catch (SQLException e) {
			sendErrorWindow(e);
		}
		finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return professeurs;
	}

	@Override
	public boolean ajouterProfesseur(Professeur professeur) {
		PreparedStatement preparedStatement = null; 

		try {
			preparedStatement = connection.prepareStatement(INSERT_PROFESSEUR);
			//{"id_prof","nom","prenom","cin","adresse","telephone", "email", "date_recrutement", "id_depart"};

			preparedStatement.setString(1, professeur.getNom());
			preparedStatement.setString(2, professeur.getPrenom());
			preparedStatement.setString(3, professeur.getCin());
			preparedStatement.setString(4, professeur.getAdresse());
			preparedStatement.setString(5, professeur.getTelephone());
			preparedStatement.setString(6, professeur.getEmail());
			preparedStatement.setDate(7, professeur.getDate_recrutement());

			if(professeur.getDepartement() == null) {
				preparedStatement.setInt(8, (Integer) null);
			}
			else {
				preparedStatement.setInt(8, professeur.getDepartement().getId_deprat());
			}


			preparedStatement.execute();

		}
		catch (SQLException e) {
			sendErrorWindow(e);
		}
		finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public boolean supprimerProfesseur(int id) {
		String query = DELETE.replace("{{TABLE_NAME}}", TABLE_PROFESSEUR).replace("{{COL_NAME}}", COL_NAMES_PROFESSEUR[0]);
		PreparedStatement preparedStatement = null; 

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1,id);
			preparedStatement.execute();

		} catch (SQLException e) {
			sendErrorWindow(e);
		}
		finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;

	}

	@Override
	public boolean modifierProfesseur(int id, Professeur nProfesseur) {
		PreparedStatement preparedStatement = null; 

		try {
			preparedStatement = connection.prepareStatement(UPDATE_PROFESSEUR);
			//{"id_prof","nom","prenom","cin","adresse","telephone", "email", "date_recrutement", "id_depart"};

			preparedStatement.setInt(9, id);
			preparedStatement.setString(1, nProfesseur.getNom());
			preparedStatement.setString(2, nProfesseur.getPrenom());
			preparedStatement.setString(3, nProfesseur.getCin());
			preparedStatement.setString(4, nProfesseur.getAdresse());
			preparedStatement.setString(5, nProfesseur.getTelephone());
			preparedStatement.setString(6, nProfesseur.getEmail());
			preparedStatement.setDate(7, nProfesseur.getDate_recrutement());
			preparedStatement.setInt(8, nProfesseur.getDepartement().getId_deprat());

			preparedStatement.executeUpdate();

		}
		catch (SQLException e) {
			sendErrorWindow(e);
		}
		finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public boolean affecterProfesseurAuDepartement(int idProfesseur, int idDepartement) {
		PreparedStatement preparedStatement = null; 

		try {
			preparedStatement = connection.prepareStatement(SET_PROF_DEP);
			preparedStatement.setInt(1,idDepartement);
			preparedStatement.setInt(2,idProfesseur);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			sendErrorWindow(e);
		}
		finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public List<Departement> getAllDepartements() {
		String query = GET_ALL.replace("{{TABLE_NAME}}", TABLE_DEPARTEMENT);
		Statement statement = null; 
		ResultSet resultSet = null;
		List<Departement>  departements = new ArrayList<Departement>(); 
		Departement departement = null;
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(query);
			while(resultSet.next()) {
				departement	 = new Departement(
						resultSet.getString(COL_NAMES_PROFESSEUR[1]));
				departement.setId_deprat(resultSet.getInt(COL_NAMES_DEPARTEMANT[0]));
				departements.add(departement);
			}
		} catch (SQLException e) {
			sendErrorWindow(e);
		}
		finally {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return departements;
	}

	@Override
	public List<Professeur> getProfesseursDuDepartement(int idDepartement) {
		String query = GET_ONE_BY_COL.replace("{{TABLE_NAME}}", TABLE_PROFESSEUR).replace("{{COL_NAME}}", COL_NAMES_PROFESSEUR[8]);
		List<Professeur> professeurs = new ArrayList<>();
		Professeur professeur;
		PreparedStatement preparedStatement = null; 
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, idDepartement);
			resultSet = preparedStatement.executeQuery();

			while(resultSet.next()) {
				professeur = new Professeur(
						resultSet.getString(COL_NAMES_PROFESSEUR[1]),
						resultSet.getString(COL_NAMES_PROFESSEUR[2]),
						resultSet.getString(COL_NAMES_PROFESSEUR[3]),
						resultSet.getString(COL_NAMES_PROFESSEUR[4]),
						resultSet.getString(COL_NAMES_PROFESSEUR[5]),
						resultSet.getString(COL_NAMES_PROFESSEUR[6]),
						resultSet.getDate(COL_NAMES_PROFESSEUR[7]));
				professeur.setId_prof(resultSet.getInt(COL_NAMES_PROFESSEUR[0]));
				//retrive departement
				//id 
				int dep_id = resultSet.getInt(COL_NAMES_PROFESSEUR[8]);
				Departement departement = getDepartementByID(dep_id);
				professeur.setDepartement(departement);
				professeurs.add(professeur);
			}

		} catch (SQLException e) {
			sendErrorWindow(e);
		}
		finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return professeurs;
	}

	@Override
	public boolean ajouterDepartement(Departement Departement) {
		PreparedStatement preparedStatement = null; 

		try {
			preparedStatement = connection.prepareStatement(INSERT_DEPARTEMENT);
			//{"id_prof","nom","prenom","cin","adresse","telephone", "email", "date_recrutement", "id_depart"};

			preparedStatement.setString(1, Departement.getNom());

			preparedStatement.execute();

		}
		catch (SQLException e) {
			sendErrorWindow(e);
		}
		finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	@Override
	public boolean supprimerDepartement(int id) {
		String query = DELETE.replace("{{TABLE_NAME}}", TABLE_DEPARTEMENT).replace("{{COL_NAME}}", COL_NAMES_DEPARTEMANT[0]);
		PreparedStatement preparedStatement = null; 

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1,id);
			preparedStatement.executeUpdate();

		} catch (MySQLIntegrityConstraintViolationException e) {
			sendErrorWindow(new Exception("Can not be deleted, because it is used in another table."));
		}catch(SQLException ex) {
			sendErrorWindow(ex);
		}
		finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				sendErrorWindow(e);
			}
		}
		return true;
	}

	@Override
	public boolean modifierDepartement(int id, Departement nDepartement) {
		PreparedStatement preparedStatement = null; 

		try {
			preparedStatement = connection.prepareStatement(UPDATE_DEPARTEMENT);
			//{"id_prof","nom","prenom","cin","adresse","telephone", "email", "date_recrutement", "id_depart"};

			preparedStatement.setString(1, nDepartement.getNom());
			preparedStatement.setInt(2, id);

			preparedStatement.execute();

		}
		catch (SQLException e) {
			sendErrorWindow(e);
		}
		finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}



	@Override
	public Departement getDepartementByID(int id) {
		String query = GET_ONE_BY_COL.replace("{{TABLE_NAME}}", TABLE_DEPARTEMENT).replace("{{COL_NAME}}", COL_NAMES_DEPARTEMANT[0]); // {{TABLE_NAME}} WHERE {{COL_NAME}}
		Departement departement = null;
		PreparedStatement preparedStatement = null; 
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				departement = new Departement(resultSet.getString(COL_NAMES_DEPARTEMANT[1]));
			}
			if(departement != null) departement.setId_deprat(id);

		} catch (SQLException e) {
			sendErrorWindow(e);
		}
		finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return departement;
	}

	@Override
	public List<Departement> getDepartementByNom(String keyWord){
		String query = GET_ALL_LIKE.replace("{{TABLE_NAME}}", TABLE_DEPARTEMENT).replace("{{COL_NAME}}", COL_NAMES_DEPARTEMANT[1]); // {{TABLE_NAME}} WHERE {{COL_NAME}}
		List<Departement> departements = new ArrayList<Departement>();
		Departement departement;
		PreparedStatement preparedStatement = null; 
		ResultSet resultSet = null;

		try {
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setString(1, "%"+keyWord+"%");
			//System.out.println(preparedStatement.toString());
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				departement = new Departement(resultSet.getString(COL_NAMES_DEPARTEMANT[1]));
				departement.setId_deprat(resultSet.getInt(COL_NAMES_DEPARTEMANT[0]));
				departements.add(departement);
			}

		} catch (SQLException e) {
			sendErrorWindow(e);
		}
		finally {
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return departements;
	}


	private void sendErrorWindow(Exception e) {
		AlertMaker.sendAlert(AlertType.ERROR, "DB_ERROR", "Data base error", e.getMessage());
		//e.printStackTrace();
	}

}
