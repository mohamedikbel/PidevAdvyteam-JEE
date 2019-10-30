package Services;

 
import java.util.List;

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
public class EmployeService {
	
	@PersistenceContext(unitName="Advyteam")
	EntityManager em;
	
	public Employe getEmployeByEmailAndPassword(String mail,String pwd)
	{
		TypedQuery<Employe> query =em.createQuery("select e from Employe e where e.email=:email and e.password=:password",Employe.class);
	    query.setParameter("email", mail);
	    query.setParameter("password", pwd);
	    
	    Employe employe=null;
	    try {
	    employe=query.getSingleResult();
	    }catch(NoResultException e) {
	    	//logger.info("Aucun employe trouve");
	    	//Logger.getLogger("Aucun employe trouve");
	    	}
	    return employe;
     }
	 
	public Employe getEmployeById(int id) {
		 Employe p = em.find(Employe.class, id);
		return p;

	}

	 

}
