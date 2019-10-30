package tn.esprit.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Projet implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

private int id;
	private String nom;
	private String description;
	private int dureeEstimee;
	private int dureeReelle;
	private Date date_debut;
	private Date date_fin;
	private Boolean isFinished;
	@OneToMany(mappedBy="projet" )
	List<Tache> taches;
	
	
	
public Projet() {
		super();
	}
public Projet(int id, String nom, String description, int dureeEstimee, int dureeReelle, Date date_debut,
			Date date_fin, Boolean isFinished) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.dureeEstimee = dureeEstimee;
		this.dureeReelle = dureeReelle;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.isFinished = isFinished;
	}
public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getDureeEstimee() {
		return dureeEstimee;
	}
	public void setDureeEstimee(int dureeEstimee) {
		this.dureeEstimee = dureeEstimee;
	}
	public List<Tache> getTaches() {
		return taches;
	}
	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}
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
public String getDesc() {
	return description;
}
public void setDesc(String desc) {
	this.description = desc;
}
public int getDureeEtimee() {
	return dureeEstimee;
}
public void setDureeEtimee(int dureeEtimee) {
	this.dureeEstimee = dureeEtimee;
}
public int getDureeReelle() {
	return dureeReelle;
}
public void setDureeReelle(int dureeReelle) {
	this.dureeReelle = dureeReelle;
}
public Date getDate_debut() {
	return date_debut;
}
public void setDate_debut(Date date_debut) {
	this.date_debut = date_debut;
}
public Date getDate_fin() {
	return date_fin;
}
public void setDate_fin(Date date_fin) {
	this.date_fin = date_fin;
}
public Boolean getIsFinished() {
	return isFinished;
}
public void setIsFinished(Boolean isFinished) {
	this.isFinished = isFinished;
}
@Override
public String toString() {
	return     nom    ;
}
 





}
