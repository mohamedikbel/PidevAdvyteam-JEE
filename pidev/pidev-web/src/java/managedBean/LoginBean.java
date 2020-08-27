package managedBean;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import Entite.Employe;
import Entite.Role;
import Services.EmployeService;
 

@ManagedBean
@SessionScoped
public class LoginBean {
	String login;
	String password;
	Employe employe;
	boolean loggedIn;
	
	@EJB
	EmployeService employeService;
	
	
	public String doLogin() {
		String navigateTo="null";
		 
		employe =employeService.getEmployeByEmailAndPassword(login, password);
		if(employe!=null && employe.getRole()==Role.Manager) {
			loggedIn=true;
			navigateTo ="/resources/pages/manager/listProjets?faces-redirect=true";
		}
		else if(employe!=null && employe.getRole()==Role.Employe) {
			loggedIn=true;
			navigateTo ="/resources/pages/employe/tabBlanc?faces-redirect=true";

		}
		else
			{loggedIn=false;
			FacesContext.getCurrentInstance().addMessage("form:btn",new FacesMessage("bad credentials"));}
		return navigateTo;
	}
	public String doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/resources/pages/login?faces-redirect=true";
	}
	
		
	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Employe getEmploye() {
		return employe;
	}
	public void setEmploye(Employe employe) {
		this.employe = employe;
	}
	public boolean isLoggedIn() {
		return loggedIn;
	}
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}
	public EmployeService getEmployeService() {
		return employeService;
	}
	public void setEmployeService(EmployeService employeService) {
		this.employeService = employeService;
	}
	
	
	

}
