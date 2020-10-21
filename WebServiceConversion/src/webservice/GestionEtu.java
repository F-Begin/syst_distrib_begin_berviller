package webservice;

import java.util.ArrayList;

public class GestionEtu {
	ArrayList<Etudiant> liste = new ArrayList<Etudiant>();
	
	public Etudiant RechercherEtu(int num) {
		for(Etudiant etu:liste) {
			if(etu.getNumero()==num) {
				return etu;
			}
		}
		return null;
	}
	
	public void ModifierNom(int num, String nom) {
		RechercherEtu(num).setNom(nom);
	}
	
	public void ModifierAdresse(int num, String adresse) {
		RechercherEtu(num).setAdresse(adresse);
	}
	
	public void AjouterNote(int num, double note) {
		RechercherEtu(num).addNote(note);
	}
	
	public Etudiant Consulter(int num) {
		return RechercherEtu(num);
	}
	
	public ArrayList<Etudiant> AfficherListe(){
		return liste;
	}
	
	public void SupprEtu(int num) {
		liste.remove(liste.indexOf(RechercherEtu(num)));
	}
}
