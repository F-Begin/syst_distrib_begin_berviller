
package fr.polytechnancy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ModifieEtudiant complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ModifieEtudiant">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="attribueModifie" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nouvelleValeur" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idEtudiant" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ModifieEtudiant", propOrder = {
    "attribueModifie",
    "nouvelleValeur",
    "idEtudiant"
})
public class ModifieEtudiant {

    protected String attribueModifie;
    protected String nouvelleValeur;
    protected int idEtudiant;

    /**
     * Gets the value of the attribueModifie property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttribueModifie() {
        return attribueModifie;
    }

    /**
     * Sets the value of the attribueModifie property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttribueModifie(String value) {
        this.attribueModifie = value;
    }

    /**
     * Gets the value of the nouvelleValeur property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNouvelleValeur() {
        return nouvelleValeur;
    }

    /**
     * Sets the value of the nouvelleValeur property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNouvelleValeur(String value) {
        this.nouvelleValeur = value;
    }

    /**
     * Gets the value of the idEtudiant property.
     * 
     */
    public int getIdEtudiant() {
        return idEtudiant;
    }

    /**
     * Sets the value of the idEtudiant property.
     * 
     */
    public void setIdEtudiant(int value) {
        this.idEtudiant = value;
    }

}
