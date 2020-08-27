package tn.esprit.service;


import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.entity.Evaluation1;


@Stateless
@LocalBean
public class Evaluation1Service {

	@PersistenceContext

	EntityManager em;
	

	public List<Evaluation1> getAllEvaluation() {
		/*TypedQuery<Evaluation> query = em.createQuery("select e from Evaluation e where id=1", Evaluation.class);
		List<Evaluation> result = query.getResultList();
		return result;*/
		Query q = em.createQuery("select e.nom , e.description from Evaluation e", Evaluation1.class);
		List<Evaluation1> results = q.getResultList();
		return results;
	}

}
