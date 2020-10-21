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
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + nom.hashCode();
        result = prime * result + adresse.hashCode();
        result = prime * result + note.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        Etudiant other = (Etudiant) obj;
        if(!this.getNom().equals(other.getNom()))
            return false;
        if(!this.adresse.equals(other.getAdresse()))
            return false;
        if(!this.note.equals(other.getNote()))
            return false;
        return true;
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
