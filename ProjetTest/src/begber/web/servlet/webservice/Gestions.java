package begber.web.servlet.webservice;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import begber.web.servlet.etudiants.Etudiant;
import begber.web.servlet.matieres.Matiere;
import begber.web.servlet.notes.Note;
import begber.web.servlet.users.User;
import begber.web.servlet.utility.PBKDFEcryption;

@WebService(name="ProjetGestionEtudiant", targetNamespace="gestions")
public class Gestions {
	private static ArrayList<Etudiant> listeEtu;
	private static ArrayList<Note> listeNote;
	private static ArrayList<Matiere> listeMatiere;
	private static ArrayList<User> listeUser;
	
	private static Connection connection;
	
	public Gestions() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e2) {
			e2.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/projetgestion?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
			System.out.println("\\u001B[31mConnexion OK !\\u001B[0m");
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Erreur ! Le service va s'arrêter. Appuyer sur entrée pour continuer.");
			try {
				System.in.read();
			} catch (IOException e1) {}
			System.exit(1);
		}
		listeEtu = new ArrayList<Etudiant>();
		listeNote = new ArrayList<Note>();
		listeMatiere = new ArrayList<Matiere>();
		listeUser = new ArrayList<User>();
		init();
	}
	
	@WebMethod(operationName = "newEtu")
	public boolean newEtu(@WebParam(name="nom")String nom, @WebParam(name="prenom")String prenom, @WebParam(name="numEtu")String numEtu) {
		PreparedStatement prepStatement = null;
		if(checkAvailabilityEtu(numEtu)) {
			try {
				prepStatement = connection.prepareStatement("INSERT INTO etudiants (`id`, `nom`, `prenom`, `numeroEtu`) VALUE (?,?,?,?)");
				prepStatement.setInt(1, listeEtu.size()+1);
				prepStatement.setString(2, nom);
				prepStatement.setString(3, prenom);
				prepStatement.setString(4, numEtu);
				prepStatement.executeUpdate();
				listeEtu.add(new Etudiant(listeEtu.size()+1, nom, prenom, numEtu));
				return true;
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	@WebMethod(operationName = "newMatiere")
	public boolean newMatiere(@WebParam(name="nom")String nom,  @WebParam(name="coeff")int coeff) {
		PreparedStatement prepStatement = null;
		if(checkAvailabilityMatiere(nom)) {
			try {
				prepStatement = connection.prepareStatement("INSERT INTO matieres (`nom`, `coeff`) VALUE (?,?)");
				prepStatement.setString(1, nom);
				prepStatement.setInt(2, coeff);
				prepStatement.executeUpdate();
				listeMatiere.add(new Matiere(nom, coeff));
				return true;
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	@WebMethod(operationName = "newNote")
	@Oneway
	public void newNote(@WebParam(name="id")String id, @WebParam(name="matieres") String matiere, @WebParam(name="note")float note) {
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO notes (`id`, `matieres`, `note`, `idNote`) VALUE (?,?,?,?)");
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, matiere);
			preparedStatement.setFloat(3, note);
			preparedStatement.setInt(4, listeNote.size()+1);
			preparedStatement.executeUpdate();
			listeNote.add(new Note(id, getMatiere(matiere).getNom(), note,listeNote.size()+1));
			System.out.println("note add");
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkAvailabilityUser(String name) {
		for (User user : listeUser) {
			if(user.getUsername().equals("name"))
				return false;
		}
		return true;
	}
	
	public boolean checkAvailabilityMatiere(String name) {
		for(Matiere mat : listeMatiere) {
			if(mat.getNom().equals(name))
				return false;
		}
		return true;
	}
	
	public boolean checkAvailabilityEtu(String numEtu) {
		for(Etudiant etu : listeEtu) {
			if(etu.getNumEtu().equals(numEtu))
				return false;
		}
		return true;
	}
	
	@WebMethod(operationName = "newUsers")
	public boolean newUsers(@WebParam(name="id")int id, @WebParam(name="username")String username, @WebParam(name="password")String password) {
		PreparedStatement preparedStatement = null;
		if(checkAvailabilityUser(username)) {
			try {
				preparedStatement = connection.prepareStatement("INSERT INTO users (`id`, `username`, `password`, `perm`) VALUE (?,?,?,?)");
				preparedStatement.setInt(1, id);
				preparedStatement.setString(2, username);
				try {
					preparedStatement.setString(3, PBKDFEcryption.generateStrongPasswordHash(password));
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (InvalidKeySpecException e) {
					e.printStackTrace();
				}
				preparedStatement.setInt(4, 0);
				preparedStatement.executeUpdate();
				listeUser.add(new User(listeUser.size()+1, username, password, 0));
				return true;
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	@WebMethod(operationName = "connexion")
	public boolean connexion(@WebParam(name="username")String username, @WebParam(name="password")String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		User user = null;
		for (User tempUser : listeUser) {
			if(tempUser.getUsername().equals(username))
				user=tempUser;
		}
		if(PBKDFEcryption.validatePassword(password, user.getPassword()))
			return true;
		return false;
	}
	
	public static void init() {
		initEtu();
		initMatiere();
		initNotes();
		initUser();
	}
	
	public static void initEtu() {
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM etudiants");
			while(resultSet.next()) {
				listeEtu.add(new Etudiant(resultSet.getInt("id"), resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("numeroEtu")));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void initMatiere() {
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM matieres");
			while(resultSet.next()) {
				listeMatiere.add(new Matiere(resultSet.getString("nom"), resultSet.getInt("coeff")));
			}
		}catch(SQLException e) {
				e.printStackTrace();
		}
	}
	
	public static void initNotes() {
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM notes");
			while(resultSet.next()) {
				listeNote.add(new Note(resultSet.getString("id"), resultSet.getString("matieres"), resultSet.getFloat("note"), resultSet.getInt("idNote")));
			}
		}catch(SQLException e) {
				e.printStackTrace();
		}
	}
	
	public static void initUser() {
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM users");
			while(resultSet.next()) {
				listeUser.add(new User(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getInt("perm")));
			}
		}catch(SQLException e) {
				e.printStackTrace();
		}
	}
	
	public Etudiant getEtuByID(int id) {
		Statement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM etudiants WHERE id = `"+id+"`");
			while(resultSet.next()) {
				return new Etudiant(resultSet.getInt("id"), resultSet.getString("nom"), resultSet.getString("prenom"), resultSet.getString("numeroEtu"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null; //pas trouvé
	}
	
	@WebMethod(operationName = "suprEtu")
	public void suprEtu(int id) {
		PreparedStatement prepStatement = null;
		
		try {
			prepStatement = connection.prepareStatement("DELETE from etudiants WHERE id=?");
			prepStatement.setInt(1, id);
			prepStatement.executeUpdate();
			listeEtu.remove(id-1);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@WebMethod(operationName = "suprMatiere")
	public void suprMatiere(String name) {
		PreparedStatement prepStatement = null;
		try {
			prepStatement = connection.prepareStatement("DELETE from matieres WHERE nom=?");
			prepStatement.setString(1, name);
			prepStatement.executeUpdate();
			for(Iterator<Matiere> iterator = listeMatiere.iterator(); iterator.hasNext();) {
				Matiere mat = iterator.next();
				if(mat.getNom().equals(name))
					iterator.remove();
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@WebMethod(operationName = "suprNote")
	public void suprNote(int idNote) {
		PreparedStatement prepStatement = null;
		try {
			prepStatement = connection.prepareStatement("DELETE from notes WHERE idNote=?");
			prepStatement.setInt(1, idNote);
			prepStatement.executeUpdate();
			listeNote.remove(idNote-1);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@WebMethod(operationName = "suprUser")
	public void suprUser(int id) {
		PreparedStatement prepStatement = null;
		try {
			prepStatement = connection.prepareStatement("DELETE from users WHERE id=?");
			prepStatement.setInt(1, id);
			prepStatement.executeUpdate();
			listeUser.remove(id);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	@WebMethod(operationName = "getListeEtu")
	public List<Etudiant> getListeEtu() {
		return listeEtu;
	}
	
	@WebMethod(operationName = "setListeEtu")
	public void setListeEtu(ArrayList<Etudiant> listeEtu) {
		Gestions.listeEtu = listeEtu;
	}
	
	@WebMethod(operationName = "getListeNote")
	public List<Note> getListeNote() {
		return listeNote;
	}
	
	@WebMethod(operationName = "setListeNote")
	public void setListeNote(ArrayList<Note> listeNote) {
		Gestions.listeNote = listeNote;
	}
	
	@WebMethod(operationName = "getListeMatiere")
	public List<Matiere> getListeMatiere() {
		return listeMatiere;
	}
	
	@WebMethod(operationName = "setListeMatiere")
	public void setListeMatiere(ArrayList<Matiere> listeMatiere) {
		Gestions.listeMatiere = listeMatiere;
	}
	
	@WebMethod(operationName = "getListeUser")
	public List<User> getListeUser() {
		return listeUser;
	}
	
	@WebMethod(operationName = "setListeUser")
	public void setListeUser(ArrayList<User> listeUser) {
		Gestions.listeUser = listeUser;
	}
	
	public Matiere getMatiere(String name) {
		for (Matiere mat : getListeMatiere()) {
			if(mat.getNom().equals(name))
				return mat;
		}
		return null;
	}
}
