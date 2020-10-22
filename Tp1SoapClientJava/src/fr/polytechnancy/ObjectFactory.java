
package fr.polytechnancy;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.polytechnancy package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _SuprEtudiant_QNAME = new QName("http://www.polytechNancy.fr", "SuprEtudiant");
    private final static QName _ExportEtu_QNAME = new QName("http://www.polytechNancy.fr", "ExportEtu");
    private final static QName _GetEtuIdResponse_QNAME = new QName("http://www.polytechNancy.fr", "GetEtuIdResponse");
    private final static QName _GetEtuId_QNAME = new QName("http://www.polytechNancy.fr", "GetEtuId");
    private final static QName _InfoAll_QNAME = new QName("http://www.polytechNancy.fr", "InfoAll");
    private final static QName _GetEtuInfoResponse_QNAME = new QName("http://www.polytechNancy.fr", "GetEtuInfoResponse");
    private final static QName _ImportEtu_QNAME = new QName("http://www.polytechNancy.fr", "ImportEtu");
    private final static QName _ModifieEtudiant_QNAME = new QName("http://www.polytechNancy.fr", "ModifieEtudiant");
    private final static QName _AjoutEtudiant_QNAME = new QName("http://www.polytechNancy.fr", "AjoutEtudiant");
    private final static QName _GetEtuInfo_QNAME = new QName("http://www.polytechNancy.fr", "GetEtuInfo");
    private final static QName _InfoAllResponse_QNAME = new QName("http://www.polytechNancy.fr", "InfoAllResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.polytechnancy
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetEtuInfoResponse }
     * 
     */
    public GetEtuInfoResponse createGetEtuInfoResponse() {
        return new GetEtuInfoResponse();
    }

    /**
     * Create an instance of {@link ModifieEtudiant }
     * 
     */
    public ModifieEtudiant createModifieEtudiant() {
        return new ModifieEtudiant();
    }

    /**
     * Create an instance of {@link InfoAllResponse }
     * 
     */
    public InfoAllResponse createInfoAllResponse() {
        return new InfoAllResponse();
    }

    /**
     * Create an instance of {@link InfoAll }
     * 
     */
    public InfoAll createInfoAll() {
        return new InfoAll();
    }

    /**
     * Create an instance of {@link SuprEtudiant }
     * 
     */
    public SuprEtudiant createSuprEtudiant() {
        return new SuprEtudiant();
    }

    /**
     * Create an instance of {@link ImportEtu }
     * 
     */
    public ImportEtu createImportEtu() {
        return new ImportEtu();
    }

    /**
     * Create an instance of {@link GetEtuInfo }
     * 
     */
    public GetEtuInfo createGetEtuInfo() {
        return new GetEtuInfo();
    }

    /**
     * Create an instance of {@link GetEtuId }
     * 
     */
    public GetEtuId createGetEtuId() {
        return new GetEtuId();
    }

    /**
     * Create an instance of {@link Etudiant }
     * 
     */
    public Etudiant createEtudiant() {
        return new Etudiant();
    }

    /**
     * Create an instance of {@link AjoutEtudiant }
     * 
     */
    public AjoutEtudiant createAjoutEtudiant() {
        return new AjoutEtudiant();
    }

    /**
     * Create an instance of {@link ExportEtu }
     * 
     */
    public ExportEtu createExportEtu() {
        return new ExportEtu();
    }

    /**
     * Create an instance of {@link GetEtuIdResponse }
     * 
     */
    public GetEtuIdResponse createGetEtuIdResponse() {
        return new GetEtuIdResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SuprEtudiant }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytechNancy.fr", name = "SuprEtudiant")
    public JAXBElement<SuprEtudiant> createSuprEtudiant(SuprEtudiant value) {
        return new JAXBElement<SuprEtudiant>(_SuprEtudiant_QNAME, SuprEtudiant.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExportEtu }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytechNancy.fr", name = "ExportEtu")
    public JAXBElement<ExportEtu> createExportEtu(ExportEtu value) {
        return new JAXBElement<ExportEtu>(_ExportEtu_QNAME, ExportEtu.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEtuIdResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytechNancy.fr", name = "GetEtuIdResponse")
    public JAXBElement<GetEtuIdResponse> createGetEtuIdResponse(GetEtuIdResponse value) {
        return new JAXBElement<GetEtuIdResponse>(_GetEtuIdResponse_QNAME, GetEtuIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEtuId }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytechNancy.fr", name = "GetEtuId")
    public JAXBElement<GetEtuId> createGetEtuId(GetEtuId value) {
        return new JAXBElement<GetEtuId>(_GetEtuId_QNAME, GetEtuId.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InfoAll }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytechNancy.fr", name = "InfoAll")
    public JAXBElement<InfoAll> createInfoAll(InfoAll value) {
        return new JAXBElement<InfoAll>(_InfoAll_QNAME, InfoAll.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEtuInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytechNancy.fr", name = "GetEtuInfoResponse")
    public JAXBElement<GetEtuInfoResponse> createGetEtuInfoResponse(GetEtuInfoResponse value) {
        return new JAXBElement<GetEtuInfoResponse>(_GetEtuInfoResponse_QNAME, GetEtuInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ImportEtu }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytechNancy.fr", name = "ImportEtu")
    public JAXBElement<ImportEtu> createImportEtu(ImportEtu value) {
        return new JAXBElement<ImportEtu>(_ImportEtu_QNAME, ImportEtu.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifieEtudiant }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytechNancy.fr", name = "ModifieEtudiant")
    public JAXBElement<ModifieEtudiant> createModifieEtudiant(ModifieEtudiant value) {
        return new JAXBElement<ModifieEtudiant>(_ModifieEtudiant_QNAME, ModifieEtudiant.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AjoutEtudiant }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytechNancy.fr", name = "AjoutEtudiant")
    public JAXBElement<AjoutEtudiant> createAjoutEtudiant(AjoutEtudiant value) {
        return new JAXBElement<AjoutEtudiant>(_AjoutEtudiant_QNAME, AjoutEtudiant.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEtuInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytechNancy.fr", name = "GetEtuInfo")
    public JAXBElement<GetEtuInfo> createGetEtuInfo(GetEtuInfo value) {
        return new JAXBElement<GetEtuInfo>(_GetEtuInfo_QNAME, GetEtuInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InfoAllResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.polytechNancy.fr", name = "InfoAllResponse")
    public JAXBElement<InfoAllResponse> createInfoAllResponse(InfoAllResponse value) {
        return new JAXBElement<InfoAllResponse>(_InfoAllResponse_QNAME, InfoAllResponse.class, null, value);
    }

}
