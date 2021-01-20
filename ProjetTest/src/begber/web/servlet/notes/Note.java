package begber.web.servlet.notes;

public class Note {
	private int idNote;
	private String idEtu;
	private String matieres;
	private float note;
	
	public Note(String idEtu, String matieres, float note, int idNote) {
		this.idNote = idNote;
		this.matieres = matieres;
		this.note = note;
		this.idEtu = idEtu;
	}
	public int getIdNote() {
		return idNote;
	}
	public void setIdNote(int id) {
		this.idNote = id;
	}
	public String getIdEtu() {
		return idEtu;
	}
	public void setIdEtu(String id) {
		this.idEtu = id;
	}
	public String getMatieres() {
		return matieres;
	}
	public void setMatieres(String matieres) {
		this.matieres = matieres;
	}
	public float getNote() {
		return note;
	}
	public void setNote(float note) {
		this.note = note;
	}
	
	
}
