package tn.esprit.service;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

import tn.esprit.entity.Document;

public interface Servicedocuremote {

	
	
 public int  AddDocument(Document d) ;
 public void deleteDocumentById(int id);
 public void AffecterDocumentEmploye(int iddoc, int iemp);
 public int AjouterDocumentB(Document d,String nom,String prenom,String email,String ROLE) throws IOException, DocumentException;

	
	
	
	
	
	
	
	
	
}
