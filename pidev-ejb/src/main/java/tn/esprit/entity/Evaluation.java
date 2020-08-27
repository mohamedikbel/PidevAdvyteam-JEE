package tn.esprit.entity;


import java.io.Serializable;


import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)

public class Evaluation implements Serializable{
	@Override
	public String toString() {
		return nom ;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id ;
	private String description ;
	private String  nom ;
	private boolean Launch ;
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd")
	private Date datedebut ;
	@JsonIgnore
	@OneToMany(mappedBy="evaluation",cascade=CascadeType.REMOVE,fetch=FetchType.EAGER)
	private List<Critere> criteres ;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (Launch ? 1231 : 1237);
		result = prime * result + (Traiter ? 1231 : 1237);
		result = prime * result + ((avis == null) ? 0 : avis.hashCode());
		result = prime * result + ((criteres == null) ? 0 : criteres.hashCode());
		result = prime * result + ((datedebut == null) ? 0 : datedebut.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((evaluationEmploye == null) ? 0 : evaluationEmploye.hashCode());
		result = prime * result + id;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Evaluation other = (Evaluation) obj;
		if (Launch != other.Launch)
			return false;
		if (Traiter != other.Traiter)
			return false;
		if (avis == null) {
			if (other.avis != null)
				return false;
		} else if (!avis.equals(other.avis))
			return false;
		if (criteres == null) {
			if (other.criteres != null)
				return false;
		} else if (!criteres.equals(other.criteres))
			return false;
		if (datedebut == null) {
			if (other.datedebut != null)
				return false;
		} else if (!datedebut.equals(other.datedebut))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (evaluationEmploye == null) {
			if (other.evaluationEmploye != null)
				return false;
		} else if (!evaluationEmploye.equals(other.evaluationEmploye))
			return false;
		if (id != other.id)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		return true;
	}
	@OneToMany(mappedBy="evaluation",fetch=FetchType.LAZY) 
	@JsonIgnore	

	private List<Avis> avis ;
	@ManyToOne
	private Employe evaluationEmploye ;
	private boolean Traiter ;
	
	

public boolean isLaunch() {
		return Launch;
	}
	public void setLaunch(boolean launch) {
		Launch = launch;
	}
	public boolean isTraiter() {
		return Traiter;
	}
	public void setTraiter(boolean traiter) {
		Traiter = traiter;
	}
public List<Avis> getAvis() {
		return avis;
	}
	public void setAvis(List<Avis> avis) {
		this.avis = avis;
	}
	public Employe getEvaluationEmploye() {
		return evaluationEmploye;
	}
	public void setEvaluationEmploye(Employe evaluationEmploye) {
		this.evaluationEmploye = evaluationEmploye;
	}

public Date getDatedebut() {
	return datedebut;
}
public void setDatedebut(Date datedebut) {
	this.datedebut = datedebut;
}




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
public Evaluation() {
	super();
	// TODO Auto-generated constructor stub
}
public Evaluation(String description2, String nom2, Date date) {
this.description=description2 ;
this.nom=nom2;
this.datedebut=date;
}
public Evaluation(String nom2, String description2, Date datedebut2, Boolean launch2, Boolean traiter2,int i) {
this.nom=nom2;
this.description=description2 ;
this.datedebut=datedebut2;
this.Launch=launch2;
this.Traiter=traiter2;
this.id=i;
}


public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}

public List<Critere> getCriteres() {
	return criteres;
}
public void setCriteres(List<Critere> criteres) {
	this.criteres = criteres;
}

}
