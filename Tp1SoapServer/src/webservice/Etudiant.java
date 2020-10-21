package webservice;

import java.io.Serializable;

public class Etudiant implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nom;
	private String prenom;
	private String email;
	
	public Etudiant() {
		
	}
	
	public Etudiant(String nom, String prenom, String email) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + nom.hashCode();
		result = prime * result + prenom.hashCode();
		result = prime * result + email.hashCode();
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
		if(!this.prenom.equals(other.getPrenom()))
			return false;
		if(!this.email.equals(other.getEmail()))
			return false;
		return true;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String toString(int id) {
		return "Etudiant n°"+id+" -> Nom : "+this.getNom()+"; Prénom : "+this.getPrenom()+"; Email : "+this.getEmail();
	}
	
}
