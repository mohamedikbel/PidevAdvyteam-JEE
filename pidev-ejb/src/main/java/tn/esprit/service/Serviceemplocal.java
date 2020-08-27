package tn.esprit.service;

import java.io.IOException;
import java.util.List;

import javax.ejb.Local;

import com.itextpdf.text.DocumentException;

import tn.esprit.entity.Document;
import tn.esprit.entity.Employe;
import tn.esprit.entity.Role;


public interface Serviceemplocal {

	public Employe getemployeemailpass(String mail ,String Pass);
	public int Addemploye(Employe e);
	void deleteEmployeById(int id);
	public List<Employe> getAllEmployes();
	public int AddemployeBeta(Role role,String image);
	public Employe getemployemail(String Mail);
	public Employe getemployemailqr(String Mail,String Qr);
	public void updatere(String Mail,String Qr,String  pass);
	
	
	
	
	
	
	
}
