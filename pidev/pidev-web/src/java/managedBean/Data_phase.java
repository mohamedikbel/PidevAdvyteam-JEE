package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import Entite.PhaseTache;
 
@ManagedBean
@SessionScoped
public class Data_phase {
	public PhaseTache[] getPhaseTaches() {
		return PhaseTache.values();
	}

}
