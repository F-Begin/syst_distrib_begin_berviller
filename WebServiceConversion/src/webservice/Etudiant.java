package webservice;

import java.io.Serializable;
import java.util.ArrayList;

public class Etudiant implements Serializable {
	private int numero;
	private String nom;
	private String adresse;
	private ArrayList<Double> note;
	
	public Etudiant() {
		
	}

	public Etudiant(int numero,String nom, String adresse, ArrayList<Double> note) {
		this.numero = numero;
		this.nom = nom;
		this.adresse = adresse;
		this.note = note;
	}
	
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	public ArrayList<Double> getNote() {
		return note;
	}

	public void addNote(double note) {
		this.note.add(note);
	}
}
