package Services;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.entity.*;

@Stateless
@LocalBean
public class TacheService {
	@PersistenceContext(unitName = "Advyteam")
	EntityManager em;

	public List<Tache> getAllTaches() {
		Query q = em.createQuery("select e from Tache e", Tache.class);
		List<Tache> results = q.getResultList();
		return results;
	}
	
	public Tache getTache(int id) {
		TypedQuery<Tache>query=em.createQuery("select e from Tache e "+"where e.id=:id",Tache.class);
		query.setParameter("id", id);
		Tache employe=null ;
		try {
		employe=query.getSingleResult();


		}catch(NoResultException e) {Logger.getAnonymousLogger().info("employe introuvable");}
		return employe;
		}
	 
	public List<Tache> getMesTaches(int idE){
		Query q = em.createQuery("select e from Tache e where e.employe.id=:p" , Tache.class);
		q.setParameter("p",idE);
		List<Tache> results = q.getResultList();
		return results;
	}
	
	
	
	
	public List<Tache> getToDoTaches(int idP) {
		Query q = em.createQuery("select e from Tache e where e.phase='ToDo' and e.projet.id=:p" , Tache.class);
		q.setParameter("p",idP);
		List<Tache> results = q.getResultList();
		return results;
	} 
	
	public List<Tache> getBasketTaches(int idP) {
		Query q = em.createQuery("select e from Tache e where e.phase is null and e.projet.id=:p" , Tache.class);
		q.setParameter("p",idP);
		List<Tache> results = q.getResultList();
		return results;
	}
	
	public List<Tache> getDoingTaches(int idP) {
		Query q = em.createQuery("select e from Tache e where e.phase='Doing' and e.projet.id=:p" , Tache.class);
		q.setParameter("p",idP);
		List<Tache> results = q.getResultList();
		//List<Tache> results = q.setParameter('p', idP).getResultList();
		return results;
	}
	
	public List<Tache> getDoneTaches(int idP) {
		Query q = em.createQuery("select e from Tache e where e.phase='Done' and e.projet.id=:p", Tache.class);
		q.setParameter("p",idP);
		List<Tache> results = q.getResultList();		 
		return results;
	}
	 
	
	
	
	
	
	
	
	
	
	
	public List<Tache> getTachesByProjet(int idP) {
		Query q = em.createQuery("select t from Tache t where t.projet.id="+idP, Tache.class);
		List<Tache> results = q.getResultList();
		return results;
	}
	
	 
	
	public int ajouter(Tache p) {
		em.persist(p);
		return p.getId();
	}

	public Tache getTacheById(int id) {
		return em.find(Tache.class, id);
	}

	public void supprimer(int id) {
		Tache e = em.find(Tache.class, id);
		em.remove(e);
	}
	public void modifier(Tache p) {
		em.merge(p);
	}

	

}
