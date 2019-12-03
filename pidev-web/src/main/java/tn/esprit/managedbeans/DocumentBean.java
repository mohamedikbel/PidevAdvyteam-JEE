package tn.esprit.managedbeans;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import com.itextpdf.text.DocumentException;

import tn.esprit.entity.Document;
import tn.esprit.entity.Employe;
import tn.esprit.service.Servicedocu;

@SessionScoped
@ManagedBean
public class DocumentBean {
	public List<Document> getDocuments() {
		documents = ser.getAllDocu();
		System.out.println(documents);
		return documents;
	}



	public void setDocuments(List<Document> documens) {
		this.documents = documens;
	}

	public Servicedocu getSer() {
		return ser;
	}



	public void setSer(Servicedocu ser) {
		this.ser = ser;
	}

	public List<Document> documents;

	private int id;
	private String nom;
    public Document getDoc() {
		return doc;
	}



	public void setDoc(Document doc) {
		this.doc = doc;
	}

	private Document doc;
	@EJB
	Servicedocu ser;
	
	
	
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

	   public int getIduser() {
		return iduser;
	}



	public void setIduser(int iduser) {
		this.iduser = iduser;
	}



	public String getNomemp() {
		return nomemp;
	}



	public void setNomemp(String nomemp) {
		this.nomemp = nomemp;
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



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}

	  @ManagedProperty(value="#{loginBean.employe.id}")
	   private int iduser;
	  
	   @ManagedProperty(value="#{loginBean.employe.nom}")
	   private String nomemp;
	   
	   @ManagedProperty(value="#{loginBean.employe.prenom}")
	   private String prenom;
	   
	   
	   @ManagedProperty(value="#{loginBean.employe.email}")
	   private String email;
	   
	   @ManagedProperty(value="#{loginBean.employe.role}")
	   private String role;

	   

	   public void GenererCV() throws IOException, DocumentException
	   {    Document op= new Document();

	        System.out.println(nomemp);
	        String nomdoc;
			nomdoc= nomemp+prenom+"-cvadvyteam.pdf";
			op.setNomdoc(nomdoc);
			int iddoc=ser.AjouterDocumentB(op, nomemp, prenom, email, role);
; 
			ser.AffecterDocumentEmploye(iddoc,1);
	   
        }

 
	   
	


   	 
    	
	
	
	
}
