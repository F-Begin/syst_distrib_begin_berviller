
package begber.web.servlet.client.gestions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for note complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="note">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idEtu" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="idNote" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="matieres" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "note", propOrder = {
    "idEtu",
    "idNote",
    "matieres",
    "note"
})
public class Note {

    protected String idEtu;
    protected int idNote;
    protected String matieres;
    protected float note;

    /**
     * Gets the value of the idEtu property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdEtu() {
        return idEtu;
    }

    /**
     * Sets the value of the idEtu property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdEtu(String value) {
        this.idEtu = value;
    }

    /**
     * Gets the value of the idNote property.
     * 
     */
    public int getIdNote() {
        return idNote;
    }

    /**
     * Sets the value of the idNote property.
     * 
     */
    public void setIdNote(int value) {
        this.idNote = value;
    }

    /**
     * Gets the value of the matieres property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMatieres() {
        return matieres;
    }

    /**
     * Sets the value of the matieres property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMatieres(String value) {
        this.matieres = value;
    }

    /**
     * Gets the value of the note property.
     * 
     */
    public float getNote() {
        return note;
    }

    /**
     * Sets the value of the note property.
     * 
     */
    public void setNote(float value) {
        this.note = value;
    }

}
