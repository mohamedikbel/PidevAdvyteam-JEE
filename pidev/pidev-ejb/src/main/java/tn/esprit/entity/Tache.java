package tn.esprit.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Tache implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String description;
	private int dureeEtimee;
	private int dureeReelle;
	private Date date_debut;
	private Date date_fin;
	private Boolean isFinished;
	@Enumerated(EnumType.STRING)
	private PhaseTache phase;

	@ManyToOne
	Projet projet;
	@ManyToOne
	Employe employe;

	// ManyToMany List<Competence> competences; get;set;

	public String getDescription() {
		return description;
	}

	public Tache() {
		super();
	}

	public Tache(int id, String nom, String description, int dureeEtimee, int dureeReelle, Date date_debut,
			Date date_fin, Boolean isFinished, PhaseTache phase) {
		super();
		this.id = id;
		this.nom = nom;
		this.description = description;
		this.dureeEtimee = dureeEtimee;
		this.dureeReelle = dureeReelle;
		this.date_debut = date_debut;
		this.date_fin = date_fin;
		this.isFinished = isFinished;
		this.phase = phase;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	public Employe getEmploye() {
		return employe;
	}

	public void setEmploye(Employe employe) {
		this.employe = employe;
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
		return dureeEtimee;
	}

	public void setDureeEtimee(int dureeEtimee) {
		this.dureeEtimee = dureeEtimee;
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

	public PhaseTache getPhase() {
		return phase;
	}

	public void setPhase(PhaseTache phase) {
		this.phase = phase;
	}

	@Override
	public String toString() {
		return "Tache [id=" + id + ", nom=" + nom + ", description=" + description + ", dureeEtimee=" + dureeEtimee
				+ ", dureeReelle=" + dureeReelle + ", date_debut=" + date_debut + ", date_fin=" + date_fin
				+ ", isFinished=" + isFinished + ", phase=" + phase + ", projet=" + projet + ", employe=" + employe
				+ "]";
	}

}
