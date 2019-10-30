package tn.esprit.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

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

public Date getDatedn() {
	return datedn;
}

public void setDatedn(Date datedn) {
	this.datedn = datedn;
}

public String getCodeqr() {
	return codeqr;
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
@Column(name="codeqr")
private String codeqr;
@Enumerated(EnumType.STRING)
@Column(name="role")
private Role role;
@Column(name="image")
private String image;






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

public Employe() {
	super();








}











}
