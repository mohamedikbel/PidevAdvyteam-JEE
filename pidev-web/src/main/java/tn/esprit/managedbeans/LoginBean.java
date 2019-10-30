package tn.esprit.managedbeans;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import tn.esprit.entity.Employe;
import tn.esprit.entity.Role;
import tn.esprit.service.*;
import tn.esprit.service.Serviceemp;

@ManagedBean(name = "loginBean")
@SessionScoped

public class LoginBean implements Serializable {


	
	public String mail;
	public String password;
	private Employe employe ;
	private boolean LoggedIn;
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	private int id;

	@EJB
	Serviceemp employeserice;


	
	
	public void setMail(String mail) {
		this.mail = mail;
	}


	public String getMail() {
		return mail;
	}
	

	public LoginBean() {
	}


	public String getPassword() {
		return password;
	}

	



	

	 void setLogin(String login) {
		this.mail = login;
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
		return LoggedIn;
	}

	public void setLoggedIn(boolean loggedIn) {
		LoggedIn = loggedIn;
	}




	
	public String doLogin()
	{
		
		String NavigateTo =null;
		employe = employeserice.getemployeemailpass(mail,password);
		System.out.print(employe);
		if (employe!= null && employe.getRole() ==  Role.admin )
		{ NavigateTo ="/advyteam/admin/AjouterEmploye?faces-redirect=true";
		
		LoggedIn =true;
		
		}
		
		else	if (employe!= null && employe.getRole() ==  Role.ing )
		{ NavigateTo ="/advyteam/employe/EmployeProfile?faces-redirect=true";
		
		LoggedIn =true;
		
		}
		
		
		else {
			FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("bad enties"));
			
		}
		
		return NavigateTo;
		
	}
	
	public void updateEmploye() {
		Employe ee = new Employe();

		ee.setId(this.employe.getId());
		ee.setNom(this.employe.getNom());
		ee.setPrenom(this.employe.getPrenom());
		ee.setEmail(this.employe.getPrenom());
		ee.setPassword(this.employe.getPassword());
		ee.setRole(this.employe.getRole());
		ee.setDatedn(this.employe.getDatedn());

		employeserice.updateEmploye(ee);
	}
	public String doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login?faces-redirect=true";
}


}
