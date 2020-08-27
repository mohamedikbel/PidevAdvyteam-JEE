package managedBean;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import Entite.Projet;
import Entite.Tache;
import Services.ProjetService;

@ManagedBean
@SessionScoped
public class ProjetBean {
	@EJB
	ProjetService projetService;

	private String nom;
	private String description;
	private int dureeEstimee;
	private int dureeReelle;
	private Date date_debut;
	private Date date_fin;
	private Boolean isFinished;
	int idToBeUpdate;

	private List<Projet> projets;
	private List<Tache> selectedTaches;

	@PostConstruct
	public void init() {
		Date date_debut = new Date();
		projets = projetService.getAllProjets();

	}

	public void ajouterProjet() {
		Projet p = new Projet();
		p.setNom(nom);
		p.setDesc(description);
		p.setDate_debut(date_debut);
		p.setDureeEtimee(dureeEstimee);
		p.setIsFinished(false);

		projetService.ajouter(p);
	}

	public String consulterProjet(Projet p) {
		String navigateTo = "null";
		navigateTo = "/resources/pages/manager/consulterProjet?faces-redirect=true";

		this.setNom(p.getNom());
		this.setDescription(p.getDesc());
		this.setDate_debut(p.getDate_debut());
		this.setDate_fin(p.getDate_fin());
		this.setDureeEstimee(p.getDureeEtimee());
		this.setDureeReelle(p.getDureeReelle());
		this.setIdToBeUpdate(p.getId());
		this.setIsFinished(p.getIsFinished());
		return navigateTo;

	}

	public void supprimer() {

		Projet p = projetService.getProjetById(idToBeUpdate);

		projetService.supprimer(p);
	}

	public String goToAjouterProjet() {
		return "/resources/pages/manager/ajouterProjet?faces-redirect=true";
	}

	public void modifier() {
		System.out.println("eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");

		Projet p = new Projet(idToBeUpdate, nom, description, dureeEstimee, dureeReelle, date_debut, date_fin,
				isFinished);
		projetService.modifier(p);
	}

	public void mettreAjour(Projet p) {
		this.setNom(p.getNom());
		this.setDescription(p.getDesc());
		this.setDate_debut(p.getDate_debut());
		this.setDate_fin(p.getDate_fin());
		this.setDureeEstimee(p.getDureeEtimee());
		this.setDureeReelle(p.getDureeReelle());
		this.setIdToBeUpdate(p.getId());
		this.setIsFinished(isFinished);
	}

	public int getIdToBeUpdate() {
		return idToBeUpdate;
	}

	public void setIdToBeUpdate(int idToBeUpdate) {
		this.idToBeUpdate = idToBeUpdate;
	}

	public List<Projet> getProjets() {
		return projets;
	}

	public void setProjets(List<Projet> projets) {
		this.projets = projets;
	}

	public ProjetService getProjetService() {
		return projetService;
	}

	public void setProjetService(ProjetService projetService) {
		this.projetService = projetService;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDureeEstimee() {
		return dureeEstimee;
	}

	public void setDureeEstimee(int dureeEstimee) {
		this.dureeEstimee = dureeEstimee;
	}

	public int getDureeReelle() {
		return dureeReelle;
	}

	public void setDureeReelle(int dureeReelle) {
		this.dureeReelle = dureeReelle;
	}

	public Date getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}

	public Date getDate_fin() {
		return date_fin;
	}

	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

	public Boolean getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(Boolean isFinished) {
		this.isFinished = isFinished;
	}

	public List<Tache> getSelectedTaches() {
		return selectedTaches;
	}

	public void setSelectedTaches(List<Tache> taches) {
		this.selectedTaches = taches;
	}

}
