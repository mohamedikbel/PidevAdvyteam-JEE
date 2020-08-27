package tn.esprit.managedbeans;

import java.io.Serializable;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tn.esprit.entity.Employe;
import tn.esprit.entity.Role;
import tn.esprit.service.*;
import tn.esprit.service.Serviceemp;

@Path("login")
@ManagedBean(name ="loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	public static String tokenpi ;

	public static String getTokenpi() {
		return tokenpi;
	}


	public static void setTokenpi(String tokenpi) {
		LoginBean.tokenpi = tokenpi;
	}
	@Context
	private UriInfo uriInfo;
    
	@EJB
	Serviceemp employeserice;
	
	private String mail;
	private String password;
	private Employe employe ;
	private boolean LoggedIn;
	private String image;
	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}


	public Part getFile() {
		return file;
	}


	public void setFile(Part file) {
		this.file = file;
	}
	private Part file;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	private int id;

	


	
	
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



	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON) 
	public Response doLogin(@FormParam("mail") String mail ,@FormParam("pass") String password )
	{
		
		String NavigateTo =null;
		//employe = employeserice.getemployeemailpass(mail,password);
		//System.out.print(employe);
		/*if (employe!= null && employe.getRole() ==  Role.admin )
		{ NavigateTo ="/advyteam/admin/AjouterEmploye?faces-redirect=true";
		
		LoggedIn =true;
		
		}
		
		else	if (employe!= null && employe.getRole() ==  Role.ing )
		{ NavigateTo ="/advyteam/employe/EmployeProfile?faces-redirect=true";
		
		LoggedIn =true;
		
		}
		
		
		else {
			FacesContext.getCurrentInstance().addMessage("form:btn", new FacesMessage("bad enties"));
			
		}*/
		//mail= MapData.get("mail");
		//password= MapData.get("password");
		System.out.println("++++++++++++++++++++++++++++++++"+mail);
		System.out.println("++++++++++++++++++++++++++++++++"+password);
		//System.out.println(employeserice.getemployeemailpass(mail,password));
		tokenpi= issueToken(mail);
		System.out.println(tokenpi);
		return Response.ok(employeserice.getemployeemailpass(mail,password)).build();
		
	}
	
	public void updateEmploye() {
		Employe ee = new Employe();
		for (String cd : file.getHeader("Content-Disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
				image = filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
			}
		}
		ee.setId(this.employe.getId());
		ee.setNom(this.employe.getNom());
		ee.setPrenom(this.employe.getPrenom());
		ee.setEmail(this.employe.getPrenom());
		ee.setPassword(this.employe.getPassword());
		ee.setRole(this.employe.getRole());
		ee.setDatedn(this.employe.getDatedn());
		ee.setImage(image);

		employeserice.updateEmploye(ee);
	}
	public String doLogout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/login?faces-redirect=true";
}

	private String issueToken(String username) {
		//Issue a token (can be a random String persisted to a database or a JWT token)
		//The issued token must be associated to a user
		//Return the issued token
		String keyString = "simplekey";
		Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "DES");
		System.out.println("the key is : " + key.hashCode());
		System.out.println("uriInfo.getAbsolutePath().toString() : " +
		uriInfo.getAbsolutePath().toString());
		//System.out.println("Expiration date: " + toDate(LocalDateTime.now().plusMinutes(15L)));
		String jwtToken =
		Jwts.builder().setSubject(username).setIssuer(uriInfo.getAbsolutePath().toString())
		.setIssuedAt(new Date()).setExpiration(toDate(LocalDateTime.now().plusMinutes(15L)))
		.signWith(SignatureAlgorithm.HS512, key).compact();
		System.out.println("the returned token is : " + jwtToken);
		return jwtToken;
        

		}
	private Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
		}

}
