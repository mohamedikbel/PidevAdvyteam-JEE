package tn.esprit.service;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.entity.Employe;



@Stateless
@LocalBean
public class Serviceemp implements Serviceempremote {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Employe getemployeemailpass(String mail, String Pass) {

	TypedQuery <Employe> query = (TypedQuery<Employe>) em.createQuery("Select e from Employe e where e.adresseMail = :mail and e.Password = :pass",Employe.class);	
	query.setParameter("mail", mail);
	query.setParameter("pass", Pass);
	
	Employe employe= null;
	
	try {
		
		employe =query.getSingleResult();		
	}
	catch (Exception e) {
	}
	
	
	return employe ;
	
	
	}

	@Override
	public int Addemploye(Employe e) {
		em.persist(e);
		return e.getId();

	}

	
	public void updateEmploye(Employe employe) { 
		Employe emp = em.find(Employe.class, employe.getId()); 
		emp.setPrenom(employe.getPrenom()); 
		emp.setNom(employe.getNom()); 
		emp.setEmail(employe.getEmail()); 
		emp.setPassword(employe.getPassword()); 
		emp.setActif(employe.isActif()); 
		emp.setRole(employe.getRole()); 
	}
	
	public void deleteEmployeById(int id) {

		em.createQuery("delete From Employe e where e.id=:id").setParameter("id", id).executeUpdate();

	}
	
	
	
	
	
	
	
	
	
}
