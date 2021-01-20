
package begber.web.servlet.client.gestions;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for newNote complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="newNote">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "newNotes", propOrder = {
    "id",
    "matieres",
    "note"
})
public class NewNote {

    protected String id;
    protected String matieres;
    protected float note;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
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
