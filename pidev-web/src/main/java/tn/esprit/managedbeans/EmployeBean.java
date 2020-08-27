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
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import tn.esprit.entity.Critere;
import tn.esprit.entity.Employe;
import tn.esprit.entity.Role;
import tn.esprit.service.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@Path("employe")
@ManagedBean(name = "employeBean")
@SessionScoped
public class EmployeBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{loginBean}")
	private  LoginBean lBean;
	@EJB
	Serviceemp employeService;
	
	@EJB
	EvaluationService evaluationservice ;
	List<Critere> critereniveau ;
	private Integer rating1;   
	private Integer rating2;   
	@ManagedProperty(value = "#{loginBean}")
	LoginBean loginBean;

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

	private List<Employe> employes;
	private int idEmployeUpdated;
	
    @GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployes()  {
		//employes = employeService.getAllEmployes();
	//	.header("Authorization", "Bearer " +lBean.tokenpi)
		System.out.println(lBean.tokenpi);
	return Response.ok(employeService.getAllEmployes()).build();
	}

	public void reload() throws IOException {

		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());

	}

	public void setFile(Part file) {
		this.file = file;
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	
	public Response AddEmploye(Employe e) {
		/*for (String cd : file.getHeader("Content-Disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				image = filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
			}
		}*/
		
		/*
		try {
			/*reload();
		} catch (IOException es) {
			// TODO Auto-generated catch block
			es.printStackTrace();
		}*/
		if(employeService.getemployemail(e.getEmail())==null)
		{	employeService.Addemploye(e);
        
		return Response.status(202).entity("Employe Ajouté").build();}
		
		return Response.status(400).entity("Email Existant").build();
         
        
  /*    System.out.println("******************************************************");

		System.out.println(e);
		 employeService.Addemploye(e);
	     return Response.status(Status.CREATED).entity("success").build();*/

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

	@PUT
	@Path("/{identif}")
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEmploye(@PathParam(value="identif") int id , Employe E) {
		Employe ee = new Employe();

		ee.setId(id);
		ee.setNom(E.getNom());
		ee.setPrenom(E.getPrenom());
		ee.setEmail(E.getEmail());
		ee.setPassword(E.getPassword());
		ee.setRole(E.getRole());
		ee.setImage(E.getImage());

		ee.setDatedn(E.getDatedn());

		employeService.updateEmploye(ee);
		return Response.ok(employeService.getAllEmployes()).build();
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
	@DELETE
	@Path("/{identif}")
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.APPLICATION_JSON)
	public Response supprimerEmploye(@PathParam(value="identif") int idEmploye) {

		employeService.deleteEmployeById(idEmploye);
        return Response.status(202).entity("Employe supprimé").build();

	}

	private boolean enabled;

	public void toggle() {
		enabled = !enabled;
	}

	public boolean isEnabled() {
		return enabled;
	}

	@POST
	@Path("/Beta")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response AjouterBeta(@FormParam("role") Role role ,@FormParam("image") String image) {

		/*for (String cd : file.getHeader("Content-Disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				image = filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
			}}*/
		employeService.AddemployeBeta(role, image);
		return Response.ok(employeService.getAllEmployes()).build();

		
		
	}

	@GET
	@Path("/{description}")
	//@Consumes(MediaType.APPLICATION_JSON) // l'entree
	@Produces(MediaType.APPLICATION_JSON) // le retour (sortie)	
	public Response recherPersonneFiltrage(@PathParam("description") String description) {
		return Response.ok(employeService.rechercherEmployeParCritere(description)).build(); 
	}
	
	
	
}
