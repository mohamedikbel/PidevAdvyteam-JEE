package tn.esprit.entity;


import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Evaluation1 {
 private int id ;
	private String description ;
	private String  nom ;
	private boolean Launch ;
	private Date datedebut ;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public boolean isLaunch() {
		return Launch;
	}
	public void setLaunch(boolean launch) {
		Launch = launch;
	}
	public Date getDatedebut() {
		return datedebut;
	}
	public void setDatedebut(Date datedebut) {
		this.datedebut = datedebut;
	}
	
	
}
