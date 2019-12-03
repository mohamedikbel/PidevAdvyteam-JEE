package tn.esprit.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
@Entity
public class Employe  {
@Id
@GeneratedValue( strategy = GenerationType.IDENTITY )
@Column(name="id")
private int id;
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

public String getPrenom() {
	return prenom;
}

public void setPrenom(String prenom) {
	this.prenom = prenom;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return Password;
}

public void setPassword(String password) {
	Password = password;
}
public String getNumtel() {
	return numtel;
}

public void setNumtel(String numtel) {
	this.numtel = numtel;
}

public String getQrlogin() {
	return qrlogin;
}

public void setQrlogin(String qrlogin) {
	this.qrlogin = qrlogin;
}

public Date getDatedn() {
	return datedn;
}

public void setDatedn(Date datedn) {
	this.datedn = datedn;
}

public String getCodeqr() {
	return codeqr;
}



public Employe(int id, String nom, String prenom, String email, String password, Date datedn, String codeqr, Role role,
		String image, String numtel, String qrlogin, List<Document> documents, List<Commentaire> commentaires) {
	super();
	this.id = id;
	this.nom = nom;
	this.prenom = prenom;
	this.email = email;
	Password = password;
	this.datedn = datedn;
	this.codeqr = codeqr;
	this.role = role;
	this.image = image;
	this.numtel = numtel;
	this.qrlogin = qrlogin;
	this.documents = documents;
	this.commentaires = commentaires;
}

public void setCodeqr(String codeqr) {
	this.codeqr = codeqr;
}

public Role getRole() {
	return role;
}

public void setRole(Role role) {
	this.role = role;
}

public List<Document> getDocuments() {
	return documents;
}

public void setDocuments(List<Document> documents) {
	this.documents = documents;
}

public String getImage() {
	return image;
}

public void setImage(String image) {
	this.image = image;
}
@Column(name="nom")
private String nom;


@Column(name="prenom")
private String prenom;
@Column(name="email")
private String email;
@Column(name="password")
private String Password;
@Column(name="datedn")
private Date datedn;
public List<Commentaire> getCommentaires() {
	return commentaires;
}

public void setCommentaires(List<Commentaire> commentaires) {
	this.commentaires = commentaires;
}
@Column(name="codeqr")
private String codeqr;
@Enumerated(EnumType.STRING)
@Column(name="role")
private Role role;
@Column(name="image")
private String image;
@Column 
private String numtel;
@Column
private String qrlogin;






public Employe(String nom, String prenom, String email, String password, Date datedn, Role role, String image) {
	super();
	this.nom = nom;
	this.prenom = prenom;
	this.email = email;
	Password = password;
	this.datedn = datedn;
	this.role = role;
	this.image = image;
}
@ManyToMany
private List<Document> documents;

@ManyToMany
private List<Commentaire>commentaires ;

public Employe() {








}













}
