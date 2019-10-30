package tn.esprit.service;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.entity.Employe;
import tn.esprit.entity.Role;


@Remote
public interface Serviceempremote {
	
	public Employe getemployeemailpass(String mail ,String Pass);
	public int Addemploye(Employe e);
	void deleteEmployeById(int id);
	public List<Employe> getAllEmployes();
	public int AddemployeBeta(Role role,String image);
	public Employe getemployemail(String Mail);
	public Employe getemployemailqr(String Mail,String Qr);
	public void updatere(String Mail,String Qr,String  pass);

	

	
	
	
	
	
	
	

}
