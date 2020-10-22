package webservice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(name="TP1 Exercice Gestion Etudiant", targetNamespace="http://www.polytechNancy.fr")
public class GestEtudiant {
	
	ArrayList<Etudiant> liste = new ArrayList<Etudiant>();
	
	@WebMethod(operationName = "GetEtuInfo")
	public Etudiant getEtuInfo(@WebParam(name="id")int id) {
		try{
			return liste.get(id);
		}catch(IndexOutOfBoundsException e) {
			e.getStackTrace();
			return null;
		}
	}
	
	@WebMethod(operationName = "ModifieEtudiant")
	@Oneway
	public void modify(@WebParam(name="attribueModifie")String args,@WebParam(name="nouvelleValeur")String value,@WebParam(name="idEtudiant")int id) {
		try {
			if(args.equals("nom"))
					liste.set(id, new Etudiant(value,liste.get(id).getPrenom(),liste.get(id).getEmail()));
			if(args.equals("prenom"))
				liste.set(id, new Etudiant(liste.get(id).getNom(),value,liste.get(id).getEmail()));
			if(args.equals("email"))
				liste.set(id, new Etudiant(liste.get(id).getNom(), liste.get(id).getPrenom(), value));
		}catch(IndexOutOfBoundsException e) {
			e.getStackTrace();
		}
	}
	
	@WebMethod(operationName = "AjoutEtudiant")
	@Oneway
	public void newEtudiant(@WebParam(name="nom") String nom,@WebParam(name="prenom") String prenom,@WebParam(name="email") String email) {
		liste.add(new Etudiant(nom,prenom,email));
	}
	
	@WebMethod(operationName = "GetEtuId")
	public int getEtuId(@WebParam(name="nom") String nom,@WebParam(name="prenom") String prenom,@WebParam(name="email") String email) {
		return liste.indexOf(new Etudiant(nom, prenom, email));
	}
	
	@WebMethod(operationName = "SuprEtudiant")
	@Oneway
	public void deleteEtudiant(@WebParam(name="id")int id) {
		try {
			liste.remove(id);
		}catch(IndexOutOfBoundsException e){
			e.getStackTrace();
		}
	}
	
	@WebMethod(operationName = "ExportEtu")
	@Oneway
	public void exportEtu() {
		try {
			FileOutputStream writeData = new FileOutputStream("etudiant.etu");
			ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
			
			writeStream.writeObject(liste);
			writeStream.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	@WebMethod(operationName = "ImportEtu")
	@Oneway
	public void importEtu() {
		try {
			FileInputStream readData = new FileInputStream("etudiant.etu");
			ObjectInputStream readStream = new ObjectInputStream(readData);
			
			liste = (ArrayList<Etudiant>) readStream.readObject();
			readStream.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@WebMethod(operationName = "InfoAll")
	public ArrayList<Etudiant> infoall() {
		return liste;
	}
}
