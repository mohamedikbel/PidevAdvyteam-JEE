package tn.esprit.service;

import java.io.IOException;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.itextpdf.text.DocumentException;

import tn.esprit.entity.Document;
import tn.esprit.entity.Employe;

@Stateless
@LocalBean
public class Servicedocu implements Servicedocuremote {
	@PersistenceContext
	private EntityManager em;
    public static final String DEST = "C://Users//PC//Pictures//";

	@Override
	public int AddDocument(Document d) {
		
		em.persist(d);
		return d.getId();

	}

	@Override
	public void deleteDocumentById(int id) {
		em.createQuery("delete From Document d where d.id=:id").setParameter("id", id).executeUpdate();

	}

	@Override
	        public void AffecterDocumentEmploye(int iddoc, int idemp) {
	        Employe emp = em.find(Employe.class, idemp);

		    Document doc= em.find(Document.class, iddoc);
			List<Document> lst = emp.getDocuments();
			lst.add(doc);
			emp.setDocuments(lst);
		}

	@Override
	public int AjouterDocumentB(Document d,String nom,String prenom,String email,String ROLE) throws IOException, DocumentException {
   
		String dist = DEST+nom+prenom+"-CVadvyteam.pdf";
		
		testpdf tp = new testpdf();
		
		testpdf.createPdf(dist,nom,prenom, email, ROLE);
		
		
		d= em.merge(d);
		
		em.persist(d);

		
		
		return d.getId();
		
		
	
	
	
	}
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
		
	}

	
	
	
	
	
	
	
	
	
	
	
	

