package tn.esprit.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity

public class Commentaire {

	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id;
	Date dc;
	String com;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDc() {
		return dc;
	}
	public void setDc(Date dc) {
		this.dc = dc;
	}
	public String getCom() {
		return com;
	}
	public void setCom(String com) {
		this.com = com;
	}
	
	@ManyToMany(mappedBy="commentaires")
	private List<Employe> employes;
	
	@ManyToMany(mappedBy="commentaires")
	private List<Post> posts;
	
	
}
