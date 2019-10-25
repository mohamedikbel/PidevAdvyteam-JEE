package tn.esprit.service;

import javax.ejb.Remote;

import tn.esprit.entity.Employe;


@Remote
public interface Serviceempremote {
	
	public Employe getemployeemailpass(String mail ,String Pass);
	public int Addemploye(Employe e);
	void deleteEmployeById(int id);

}
