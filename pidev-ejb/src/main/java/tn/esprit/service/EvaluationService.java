package tn.esprit.service;


import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.entity.Critere;
import tn.esprit.entity.Evaluation;

@Stateless
@LocalBean

public class EvaluationService {
	@PersistenceContext
	EntityManager em;

	// CRUD Evaluation
	public int AddEvaluation(Evaluation e) {
		em.persist(e);
		return e.getId();
	}

	public void affecterEvaluationCritere(int idEmp, List<String> critereselectionner) {
//	System.out.println(idEmp+idContrat+"aaaaaaaa");
		Evaluation e = em.find(Evaluation.class, idEmp);
		for (String i : critereselectionner) {
			if (e != null) {
				Critere c = new Critere();
				c.setDescription(i);
				// Critere q=this.findByDescription( i);
				c.setEvaluation(e);
				em.merge(c);
			}
		}
	}
	
		public List<Evaluation> recupereEvalByIdCritere(int id) {
			TypedQuery<Evaluation> query = em.createQuery("select c from Evaluation c  where c.id=:id", Evaluation.class);
			query.setParameter("id", id);
			List<Evaluation> result = query.getResultList();

			return result;

			// return 0;
		}
		

	private Critere findByIdEval(int idEmp) {
		Query query = em.createQuery("select e from Critere e where e.evaluation.id =:idEmp", Critere.class);
		Critere result = (Critere) query.setParameter("idEmp", idEmp).getSingleResult();
		return result;
	}

	private Critere findByDescription(String i) {
		Query query = em.createQuery("select e from Critere e where e.description =:i", Critere.class);
		Critere result = (Critere) query.setParameter("i", i).getSingleResult();
		return result;
	}

	public List<Evaluation> getAllEvaluationLaunche() {
		TypedQuery<Evaluation> query = em.createQuery("select e from Evaluation e where e.Launch=1 and e.Traiter=0",
				Evaluation.class);
		List<Evaluation> result = query.getResultList();
		return result;
	}

	public List<Evaluation> getAllEvaluationTraiter() {
		TypedQuery<Evaluation> query = em.createQuery("select e from Evaluation e where e.Traiter=1 ",
				Evaluation.class);
		List<Evaluation> result = query.getResultList();
		return result;
	}

	
	public List<Evaluation> getAllEvaluation() {
		TypedQuery<Evaluation> query = em.createQuery("select e from Evaluation e", Evaluation.class);
		List<Evaluation> result = query.getResultList();
		return result;
	}

	public void deleteEvalId(int e) {
		em.remove(em.find(Evaluation.class, e));

	}

	public void updateEvaluation(Evaluation dep) {
		em.merge(dep);
	}

	
	public void affecterCritereEvalution( Integer selectedEvaluationId, Integer selectedCritereId) {
		Evaluation e = em.find(Evaluation.class, selectedEvaluationId);
		
		Critere q = em.find(Critere.class, selectedCritereId);
		q.setEvaluation(e);
		em.merge(q);
	}

	public void isLaunch(int id) {

		em.createQuery("update Evaluation  set Launch = 1 where id = :id ").setParameter("id", id).executeUpdate();
	}

	public void updateEvaluationCritere(int id, List<String> critereselectionner) {

		em.createQuery("update Critere  set Launch = 1 where id = :id ").setParameter("id", id).executeUpdate();
	}

	public void updateEvaluationCritere(int id, String i) {
		em.createQuery("update Critere  set description = i where evaluation.id = :id ").setParameter("id", id)
				.executeUpdate();

	}

	public void updatecritereniveau(String c, String niveau, int id) {
		System.out.println("je suis la");
		em.createQuery("update Critere  set niveau = ?   where id = :id ").setParameter(1, niveau)
				.setParameter("id", id).executeUpdate();
	}

}
