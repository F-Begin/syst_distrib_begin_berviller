import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import fr.polytechnancy.Etudiant;
import fr.polytechnancy.GestEtudiantService;
import fr.polytechnancy.TP1_0020Exercice_0020Gestion_0020Etudiant;

public class ClientJava {
	
	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	final static TP1_0020Exercice_0020Gestion_0020Etudiant stub = new GestEtudiantService().getTP1_0020Exercice_0020Gestion_0020EtudiantPort();
	
	public static boolean isNullOrEmpty(String str) {
		if(str != null && !str.trim().isEmpty())
			return false;
		return true;
	}
	
	public static boolean isInteger(String str) {
	    if (str == null) {
	        return false;
	    }
	    int length = str.length();
	    if (length == 0) {
	        return false;
	    }
	    int i = 0;
	    if (str.charAt(0) == '-') {
	        if (length == 1) {
	            return false;
	        }
	        i = 1;
	    }
	    for (; i < length; i++) {
	        char c = str.charAt(i);
	        if (c < '0' || c > '9') {
	            return false;
	        }
	    }
	    return true;
	}
	
	public static void ajouterEtu() {
		String nom = null;
		String prenom = null;
		String email = null;
		System.out.println("Donner un nom : ");
		try {
			nom = reader.readLine();
			System.out.println("Donner un pr�nom : ");
			prenom = reader.readLine();
			System.out.println("Donner une adresse mail : ");
			email = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(!isNullOrEmpty(nom) && !isNullOrEmpty(prenom) && !isNullOrEmpty(email)) {
			stub.ajoutEtudiant(nom, prenom, email);
			System.out.println("Etudiant ajout� !");
		}
	}
	
	public static void suprEtu() {
		int id = -1;
		String answer = null;
		System.out.println("Donnez un ID : ");
		try {
			answer = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(isInteger(answer)) {
			id = Integer.parseInt(answer);
			stub.suprEtudiant(id);
			System.out.println("Etudiant supprim� !");
		}
		else
			System.out.println("Erreur : ID incorrecte !");
	}
	
	public static void getID() {
		String nom = null;
		String prenom = null;
		String email = null;
		System.out.println("Donner un nom : ");
		try {
			nom = reader.readLine();
			System.out.println("Donner un pr�nom : ");
			prenom = reader.readLine();
			System.out.println("Donner une adresse mail : ");
			email = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(!isNullOrEmpty(nom) && !isNullOrEmpty(prenom) && !isNullOrEmpty(email)) {
			int id = stub.getEtuId(nom, prenom, email);
			if(!(id == -1))
				System.out.println("L'�tudiant poss�de l'ID : "+id);
			else
				System.out.println("Etudiant introuvable !");
		}
	}
	
	public static void getInfo() {
		int id = -1;
		String answer = null;
		System.out.println("Donnez un ID : ");
		try {
			answer = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(isInteger(answer)) {
			id = Integer.parseInt(answer);
			Etudiant etu = stub.getEtuInfo(id);
			System.out.println("Etudiant n�"+id);
			System.out.println("Nom : "+etu.getNom());
			System.out.println("Pr�nom : "+etu.getPrenom());
			System.out.println("Email : "+etu.getEmail());
		}
		else
			System.out.println("Erreur : ID incorrecte !");
	}
	
	public static void modifyEtu() {
		String args = null;
		System.out.println("Que voulez vous modifier ?");
		System.out.println("1 : Le nom de l'�tudiant.");
		System.out.println("2 : Le pr�nom de l'�tudiant.");
		System.out.println("3 : L'adresse mail de l'�tudiant.");
		try {
			args = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(isInteger(args)) {
			modifyEtuArgs(Integer.parseInt(args));
		}
		else
			System.out.println("Erreur : R�ponse incorrecte !");
	}
	
	public static void modifyEtuArgs(int args) {
		String answer = null;
		System.out.println("Veuillez donner l'ID de l'�tudiant � modifier : ");
		try {
			answer = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(isInteger(answer)) {
			modifyEtuArgsID(args, Integer.parseInt(answer));
		}
		else
			System.out.println("Erreur : ID incorrecte !");
	}
	
	public static void modifyEtuArgsID(int args, int id) {
		String value = null;
		String modify = null;
		switch(args) {
		case 1:
			System.out.println("Nouveau nom : ");
			modify = "nom";
			break;
		case 2:
			System.out.println("Nouveau pr�nom : ");
			modify = "prenom";
			break;
		case 3:
			System.out.println("Nouvelle adresse email :");
			modify = "email";
			break;
		}
		try {
			value = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(!isNullOrEmpty(value) && !isNullOrEmpty(modify)) {
			stub.modifieEtudiant(modify, value, id);
			System.out.println("Etudiant modifi� !");
		}
	}
	
	public static void infoAll() {
		ArrayList<Etudiant> liste = (ArrayList<Etudiant>) stub.infoAll();
		for(Etudiant etu : liste) {
			System.out.println("Etudiant n�"+liste.indexOf(etu));
			System.out.println("Nom : "+etu.getNom());
			System.out.println("Pr�nom : "+etu.getPrenom());
			System.out.println("Email : "+etu.getEmail());
			System.out.println("-------------------------------------");
		}
	}
	
	public static void importExport() {
		String answer = null;
		System.out.println("Voulez vous :");
		System.out.println("1 : Importer des donn�es.");
		System.out.println("2 : Exporter des donn�es.");
		try {
			answer = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(isInteger(answer)) {
			switch(Integer.parseInt(answer)) {
			case 1:
				stub.importEtu();
				System.out.println("Importation effectu�e !");
				break;
			case 2:
				stub.exportEtu();
				System.out.println("Exportation effectu�e !");
				break;
			default:
				System.out.println("Erreur : R�ponse Incorrecte !");
			}
		}
	}

	public static void main(String[] args) {
		
		boolean work = true;
		
		while(work) {
			System.out.println("Bienvenue ! Que voulez vous faire ?");
			System.out.println("1 : Ajouter un �tudiant.");
			System.out.println("2 : Supprimer un �tudiant.");
			System.out.println("3 : Obtenir l'ID d'un �tudiant.");
			System.out.println("4 : Obtenir les informations sur un �tudiant.");
			System.out.println("5 : Modifier les informations d'un �tudiant.");
			System.out.println("6 : Obtenir les informations de tout les �tudiant.");
			System.out.println("7 : Importer ou Exporter.");
			System.out.println("8 : S'arr�ter l�.");
			System.out.println();
			String answer = null;
			try {
				answer = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(isInteger(answer)) {
				switch(Integer.parseInt(answer)) {
				case 1:
					ajouterEtu();
					break;
				case 2:
					suprEtu();
					break;
				case 3:
					getID();
					break;
				case 4:
					getInfo();
					break;
				case 5:
					modifyEtu();
					break;
				case 6:
					infoAll();
					break;
				case 7:
					importExport();
					break;
				case 8:
					work = false;
					break;
				default:
					System.out.println("Erreur : R�ponse Incorrecte !");
				}
			}
		}
	}
}