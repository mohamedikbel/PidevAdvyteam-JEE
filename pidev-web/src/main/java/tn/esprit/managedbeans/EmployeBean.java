package tn.esprit.managedbeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import tn.esprit.entity.Employe;
import tn.esprit.entity.Role;
import tn.esprit.service.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@ManagedBean(name = "employeBean")
@SessionScoped
public class EmployeBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	Serviceemp employeService;
	private String prenom;

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private String nom;

	public Date getDatedn() {
		return datedn;
	}

	public void setDatedn(Date datedn) {
		this.datedn = datedn;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	private String password;
	private String email;
	private Role role;
	private Date datedn;
	private String image;
	private Part file;

	public Part getFile() {
		return file;
	}

	public List<Employe> employes;

	public int idEmployeUpdated;

	public List<Employe> getEmployes() {
		employes = employeService.getAllEmployes();
		return employes;
	}

	public void reload() throws IOException {

		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());

	}

	public void setFile(Part file) {
		this.file = file;
	}

	public void AddEmploye() {
		for (String cd : file.getHeader("Content-Disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				image = filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
			}
		}
		employeService.Addemploye(new Employe(nom, prenom, email, password, datedn, role, image));
		try {
			reload();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void initialisation() {
		prenom = null;
		nom = null;
		password = null;
		email = null;
		role = null;
		image = null;
		datedn = null;

	}

	public void setIdEmployeUpdated(int idEmployeUpdated) {
		this.idEmployeUpdated = idEmployeUpdated;
	}

	public int getIdEmployeUpdated() {
		return idEmployeUpdated;
	}

	public void recupererEmploye(Employe e) {
		initialisation();
		prenom = e.getPrenom();
		nom = e.getNom();
		password = e.getPassword();
		email = e.getEmail();
		role = e.getRole();
		datedn = e.getDatedn();
		this.setIdEmployeUpdated(e.getId());

	}

	public void updateEmploye() {
		Employe ee = new Employe();

		ee.setId(this.getIdEmployeUpdated());
		ee.setNom(nom);
		ee.setPrenom(prenom);
		ee.setEmail(email);
		ee.setPassword(password);
		ee.setRole(role);
		ee.setDatedn(datedn);

		employeService.updateEmploye(ee);
	}

	public void updateEmploye(Employe ee) {
		ee.setNom(nom);
		ee.setPrenom(prenom);
		ee.setEmail(email);
		ee.setPassword(password);
		ee.setRole(role);
		ee.setDatedn(datedn);

		employeService.updateEmploye(ee);
	}

	public void supprimerEmploye(int idEmploye) {

		employeService.deleteEmployeById(idEmploye);

	}

	private boolean enabled;

	public void toggle() {
		enabled = !enabled;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void AjouterBeta() {

		for (String cd : file.getHeader("Content-Disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				image = filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
			}
		}
		employeService.AddemployeBeta(role, image);
	}

}
