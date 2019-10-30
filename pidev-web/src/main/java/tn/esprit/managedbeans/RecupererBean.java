package tn.esprit.managedbeans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import tn.esprit.entity.Employe;
import tn.esprit.service.*;

@ManagedBean (name="recupererBean")
@SessionScoped
public class RecupererBean {
	
	
	
	
	
	
	private String mail;
	private String qr ;
	private String mailr;
	private String qrr ;
	private Employe employe;
	private String pass;
	

	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Employe getEmploye() {
		return employe;
	}
	public void setEmploye(Employe employe) {
		this.employe = employe;
	}

	public String getMailr() {
		return mailr;
	}
	public void setMailr(String mailr) {
		this.mailr = mailr;
	}
	public String getQrr() {
		return qrr;
	}
	public void setQrr(String qrr) {
		this.qrr = qrr;
	}

	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getQr() {
		return qr;
	}
	public void setQr(String qr) {
		this.qr = qr;
	}
	
	@EJB
    Serviceemp ser;	
	
	
	
	public String recupercompte() throws AddressException, MessagingException
	{ Mailtest send = new Mailtest();
	  employe = ser.getemployemail(mail);
		if (employe!=null)
			
		{	 
			  System.out.println(mail);
			  String a="http://localhost:9080/pidev-web/Nouveaum.jsf?mailr="+ser.getemployemail(mail).getEmail()+"&&qrr="+ser.getemployemail(mail).getCodeqr();
			  String body= "Cliquer sur ce lien pour recuperer votre compte.<br><br> "+ a + "<br><br> Bonne Retour, <br>Advyteam Support";
              send.generateAndSendEmail("Reinstaller Mot de passe",body,ser.getemployemail(mail).getEmail());                                       
              return "/success?faces-redirect=true";

	}
		
		
		return mail;
		
		
		
		
		
		
	}
	

	public String onload() { 

	
	System.out.println(mailr);
	System.out.println(qrr);
	employe = ser.getemployemailqr(mailr,qrr);
	if(employe!=null)
	{	System.out.println(employe.getNom());

        return "/changermp?faces-redirect=true";
	}
	
	else if (employe==null)
	{
		return "/error?faces-redirect=true";
	
	}
	
	return "";
	}
	public String changer()
	{
		
		ser.updatere(mailr,qrr,pass);
	    return "/login?faces-redirect=true";
			
	}
}
