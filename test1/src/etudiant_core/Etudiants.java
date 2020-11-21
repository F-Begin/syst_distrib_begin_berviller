package etudiant_core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


//couche métier de l'application
public class Etudiants {
	
	private Connection connection; // la connecxion
	
	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}


	public void connect() {
		//chargement du driver MySql..
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // exception surveillée...
		} catch (ClassNotFoundException e) {
					System.out.println("Le driver n'est pas chargé...");
		}
		try {
			// connexion
			connection = DriverManager.getConnection("jdbc:mysql://localhost/test1?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		}catch (SQLException e) {
			System.out.println("La BD n'est pas connectée");
		}
	}
	
	
	public List<Etudiant> allStudentsDisplay(){
		List<Etudiant> result = new ArrayList<Etudiant>();
		
		this.connect();
		Statement statement=null;
		ResultSet resultSet=null;
		
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery("SELECT * FROM etudiants");
			
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				String nom = resultSet.getString("nom");
				String prenom = resultSet.getString("prenom");
				result.add(new Etudiant(id, nom, prenom));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(connection!=null) connection.close();
				if(statement!=null) statement.close();
				if(resultSet!=null) statement.close();
			} catch (Exception e) {
			e.printStackTrace();
			}	
		}
		return result;
	}
	
	public int getCount() {
		this.connect();
		Statement statement=null;
		ResultSet result=null;
		try {
			statement = connection.createStatement();
			result = statement.executeQuery("SELECT COUNT(*) AS total FROM `etudiants`");
			result.next();
			return result.getInt("total");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(connection!=null) connection.close();
				if(statement!=null) statement.close();
				if(result!=null) statement.close();
			} catch(Exception e) {
			e.printStackTrace();
			}
		}
		return -1; //Erreur si retourne -1
	}
	
	public void addStudent(String nom, String prenom) {
		this.connect();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.connection.prepareStatement("INSERT INTO `etudiants`(`id`, `nom`, `prenom`) VALUES (?,?,?);");
			preparedStatement.setInt(1, getCount()+1);
			preparedStatement.setString(2, nom);
			preparedStatement.setString(3, prenom);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(connection!=null) connection.close();
				if(preparedStatement!=null) preparedStatement.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void suprStudent(int id) {
		this.connect();
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = this.connection.prepareStatement("DELETE FROM `etudiants` WHERE `id`=?");
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(connection!=null) connection.close();
				if(preparedStatement!=null) preparedStatement.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void modEtu(String args, int id, String newValue) {
		this.connect();
		PreparedStatement preparedStatement = null;
		try {
			if(args.equals("nom")) {
				preparedStatement = this.connection.prepareStatement("UPDATE `etudiants` set `nom`=? WHERE `id`=?");
				preparedStatement.setInt(2, id);
				preparedStatement.setString(1, newValue);
			}
			else if(args.equals("prenom")) {
				preparedStatement = this.connection.prepareStatement("UPDATE `etudiants` set `prenom`=? WHERE `id`=?");
				preparedStatement.setInt(2, id);
				preparedStatement.setString(1, newValue);
			}
			preparedStatement.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(connection!=null) connection.close();
				if(preparedStatement!=null) preparedStatement.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
