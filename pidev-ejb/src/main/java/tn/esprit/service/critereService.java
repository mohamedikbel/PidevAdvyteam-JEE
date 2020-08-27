package tn.esprit.service;


import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.entity.Critere;
import tn.esprit.entity.Evaluation;



@Stateless
@LocalBean

public class critereService {
	@PersistenceContext
	EntityManager em;
	
	public 	int AddCritere(Critere e) {
		em.merge(e);

		return e.getId();
	}
	
 	public void ajouter(Critere p) {

		em.merge(p);
   	}
	

		public List<Critere> RecupCritereById(int id) {
			TypedQuery<Critere> query = em.createQuery("select c from Critere c  where c.id=:id", Critere.class);
			query.setParameter("id", id);
			List<Critere> result = query.getResultList();

			return result;

			// return 0;
		}
	
	
 	public void ajoutercritere(Critere p) {
		Evaluation e=new Evaluation();
		e.setId(3);
		p.setEvaluation(e);
		//p.getEvaluation().setId(7);		
		em.merge(p);
   	}

	public List<Critere> getAllEvaluation() {
		TypedQuery<Critere> query = em.createQuery("select e from Critere e", Critere.class);
		List<Critere> result = query.getResultList();
		return result;
	}

	public List<String> getAllEvaluationDescription() {
		TypedQuery<String> query = em.createQuery("select e.description from Critere e", String.class);
		List<String> result = query.getResultList();
		return result;
	}
	
	public List< Critere> getDescription(int a) {
		TypedQuery<Critere> query = em.createQuery("select c from Critere c  where c.id=:a",
				Critere.class);
		List<Critere> result = query.setParameter("a", a).getResultList();

		return result;
	}

	public String deleteCriId( int e) {
		em.remove(em.find(Critere.class, e));
return("supprimer zvec succes");
	}

	
	public void updatecritere(Critere c) {

		em.createQuery("update Critere  set description = ?  where id=:id")
				.setParameter("id", c.getId()).setParameter(1, c.getDescription()).executeUpdate();
		em.createQuery("update Critere  set niveau = ?   where id=:id")
		.setParameter("id", c.getId()).setParameter(1, c.getNiveau()).executeUpdate();

	}
}
