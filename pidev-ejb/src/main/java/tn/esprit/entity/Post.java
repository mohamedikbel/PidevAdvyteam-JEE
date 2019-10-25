package tn.esprit.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Post {

	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getBody() {
		return body;
	}



	public void setBody(String body) {
		this.body = body;
	}



	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private int id ;
	
	
	
	private String body ;
	
	
	
	
	
	
	
}
