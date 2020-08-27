package tn.esprit.entity;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;
@Entity
@XmlRootElement

public class Evaluation360 implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id ;

@ManyToOne
private Employe evaluationEmploye360 ;
@OneToMany(mappedBy="commentaireEvzl360")
private List<Commentaire> commentaires ;
private Date datedebut ;
	
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
	public Employe getEvaluationEmploye360() {
		return evaluationEmploye360;
	}
	public void setEvaluationEmploye360(Employe evaluationEmploye360) {
		this.evaluationEmploye360 = evaluationEmploye360;
	}
	public List<Commentaire> getCommentaires() {
		return commentaires;
	}
	public void setCommentaires(List<Commentaire> commentaires) {
		this.commentaires = commentaires;
	}
	
}
