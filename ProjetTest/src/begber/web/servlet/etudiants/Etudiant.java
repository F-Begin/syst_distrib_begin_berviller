package begber.web.servlet.etudiants;

public class Etudiant {
	private int id;
	private String nom;
	private String prenom;
	private String numEtu;
	
	public Etudiant(int id, String nom, String prenom, String numEtu) {
		this.id=id;
		this.nom=nom;
		this.prenom=prenom;
		this.numEtu=numEtu;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getNumEtu() {
		return numEtu;
	}
	public void setNumEtu(String numEtu) {
		this.numEtu = numEtu;
	}
}
