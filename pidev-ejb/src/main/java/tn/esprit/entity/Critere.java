package tn.esprit.entity;


import java.io.Serializable;





import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties

public class Critere implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id ;
private String niveau ;
private String description ;
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
@ManyToOne(fetch=FetchType.EAGER)
@JoinColumn(name="emlployeid")

private Evaluation evaluation  ;

public Critere() {
	super();

}
public Critere( String niveau2,String descri) {
this.niveau=niveau2;
this.description=descri;
}
public Critere(String description2, String niveau2, int setIdBeUpdate) {
this.description=description2;
this.niveau=niveau2;
this.id=setIdBeUpdate;}

@Override
public String toString() {
	return description;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getNiveau() {
	return niveau;
}
public void setNiveau(String niveau) {
	this.niveau = niveau;
}
public Evaluation getEvaluation() {
	return evaluation;
}
public void setEvaluation(Evaluation evaluation) {
	this.evaluation = evaluation;
} 

}
