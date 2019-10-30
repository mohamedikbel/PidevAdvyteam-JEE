package Services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import tn.esprit.entity.*;
 
@Stateless
@LocalBean
public class ProjetService implements ProjetServiceRemote {
	@PersistenceContext(unitName = "Advyteam")
	EntityManager em;

	@EJB
	TacheService ts = new TacheService();

	@Override
	public int ajouter(Projet p) {
		em.persist(p);
		return p.getId();
	}

	public Projet getProjetById(int id) {
		 Projet p = em.find(Projet.class, id);
 		return p;

	}

	public void modifier(Projet p) {
		em.merge(p);
	}

	public void supprimer(Projet p) {
		List<Tache> t = ts.getTachesByProjet(p.getId());
		if (t != null) {
			for (Tache t1 : t) {
				t1.setProjet(null);
				em.merge(t1);
			}
		}
		em.remove(em.find(Projet.class, p.getId()));
	}

	public List<Projet> getAllProjets() {
		Query q = em.createQuery("select e from Projet e", Projet.class);
		List<Projet> results = q.getResultList();
		return results;
	}

}
