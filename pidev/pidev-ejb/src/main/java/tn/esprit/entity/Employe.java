package tn.esprit.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Employe  {
@Id
@GeneratedValue( strategy = GenerationType.IDENTITY )
@Column(name="EM_Id")
private int id;
@Column(name="nom")
private String nom;
@Column(name="prenom")
private String prenom;
@Column(name="email")
private String email;
@Column(name="EM_Password")
private String Password;
@Column(name="datedn")
private Date datedn;
@Column(name="codeqr")
private String codeqr;
@Enumerated(EnumType.STRING)
@Column(name="EM_Role")
private Role role;





public Employe() {
	super();
}


}
