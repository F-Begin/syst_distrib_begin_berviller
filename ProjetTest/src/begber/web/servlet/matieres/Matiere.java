package begber.web.servlet.matieres;

public class Matiere {
	private String nom;
	private int coeff;
	
	public Matiere(String nom, int coeff) {
		this.nom=nom;
		this.coeff=coeff;
	}
	
	public Matiere() {}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getCoeff() {
		return coeff;
	}

	public void setCoeff(int coeff) {
		this.coeff = coeff;
	}
}
