
package fr.polytechnancy;

import java.util.List;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "TP1 Exercice Gestion Etudiant", targetNamespace = "http://www.polytechNancy.fr")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface TP1_0020Exercice_0020Gestion_0020Etudiant {


    /**
     * 
     * @param id
     * @return
     *     returns fr.polytechnancy.Etudiant
     */
    @WebMethod(operationName = "GetEtuInfo")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "GetEtuInfo", targetNamespace = "http://www.polytechNancy.fr", className = "fr.polytechnancy.GetEtuInfo")
    @ResponseWrapper(localName = "GetEtuInfoResponse", targetNamespace = "http://www.polytechNancy.fr", className = "fr.polytechnancy.GetEtuInfoResponse")
    public Etudiant getEtuInfo(
        @WebParam(name = "id", targetNamespace = "")
        int id);

    /**
     * 
     * @param idEtudiant
     * @param nouvelleValeur
     * @param attribueModifie
     */
    @WebMethod(operationName = "ModifieEtudiant")
    @Oneway
    @RequestWrapper(localName = "ModifieEtudiant", targetNamespace = "http://www.polytechNancy.fr", className = "fr.polytechnancy.ModifieEtudiant")
    public void modifieEtudiant(
        @WebParam(name = "attribueModifie", targetNamespace = "")
        String attribueModifie,
        @WebParam(name = "nouvelleValeur", targetNamespace = "")
        String nouvelleValeur,
        @WebParam(name = "idEtudiant", targetNamespace = "")
        int idEtudiant);

    /**
     * 
     * @param prenom
     * @param email
     * @param nom
     */
    @WebMethod(operationName = "AjoutEtudiant")
    @Oneway
    @RequestWrapper(localName = "AjoutEtudiant", targetNamespace = "http://www.polytechNancy.fr", className = "fr.polytechnancy.AjoutEtudiant")
    public void ajoutEtudiant(
        @WebParam(name = "nom", targetNamespace = "")
        String nom,
        @WebParam(name = "prenom", targetNamespace = "")
        String prenom,
        @WebParam(name = "email", targetNamespace = "")
        String email);

    /**
     * 
     * @param prenom
     * @param email
     * @param nom
     * @return
     *     returns int
     */
    @WebMethod(operationName = "GetEtuId")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "GetEtuId", targetNamespace = "http://www.polytechNancy.fr", className = "fr.polytechnancy.GetEtuId")
    @ResponseWrapper(localName = "GetEtuIdResponse", targetNamespace = "http://www.polytechNancy.fr", className = "fr.polytechnancy.GetEtuIdResponse")
    public int getEtuId(
        @WebParam(name = "nom", targetNamespace = "")
        String nom,
        @WebParam(name = "prenom", targetNamespace = "")
        String prenom,
        @WebParam(name = "email", targetNamespace = "")
        String email);

    /**
     * 
     * @param id
     */
    @WebMethod(operationName = "SuprEtudiant")
    @Oneway
    @RequestWrapper(localName = "SuprEtudiant", targetNamespace = "http://www.polytechNancy.fr", className = "fr.polytechnancy.SuprEtudiant")
    public void suprEtudiant(
        @WebParam(name = "id", targetNamespace = "")
        int id);

    /**
     * 
     */
    @WebMethod(operationName = "ExportEtu")
    @Oneway
    @RequestWrapper(localName = "ExportEtu", targetNamespace = "http://www.polytechNancy.fr", className = "fr.polytechnancy.ExportEtu")
    public void exportEtu();

    /**
     * 
     */
    @WebMethod(operationName = "ImportEtu")
    @Oneway
    @RequestWrapper(localName = "ImportEtu", targetNamespace = "http://www.polytechNancy.fr", className = "fr.polytechnancy.ImportEtu")
    public void importEtu();

    /**
     * 
     * @return
     *     returns java.util.List<fr.polytechnancy.Etudiant>
     */
    @WebMethod(operationName = "InfoAll")
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "InfoAll", targetNamespace = "http://www.polytechNancy.fr", className = "fr.polytechnancy.InfoAll")
    @ResponseWrapper(localName = "InfoAllResponse", targetNamespace = "http://www.polytechNancy.fr", className = "fr.polytechnancy.InfoAllResponse")
    public List<Etudiant> infoAll();

}
