package Services;

import javax.ejb.Remote;

import tn.esprit.entity.*;

@Remote
public interface ProjetServiceRemote {
	
	public int ajouter(Projet p) ;

}
