package tn.esprit.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Document {
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNomdoc() {
		return nomdoc;
	}


	public void setNomdoc(String nomdoc) {
		this.nomdoc = nomdoc;
	}


	public List<Employe> getEmployes() {
		return employes;
	}


	public void setEmployes(List<Employe> employes) {
		this.employes = employes;
	}


	private String nomdoc;
	
	
	@ManyToMany(mappedBy="documents")
	private List<Employe> employes;
   
	
	
	

}
