package tn.esprit.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity

public class Commentaire {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id ;

@ManyToOne 
private Employe employecommentaire ;
@ManyToOne
private Evaluation360 commentaireEvzl360 ;
	
}
