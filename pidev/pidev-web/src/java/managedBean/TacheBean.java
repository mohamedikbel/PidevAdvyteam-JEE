package managedBean;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ValueExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.event.DragDropEvent;

import Entite.Employe;
import Entite.PhaseTache;
import Entite.Projet;
import Entite.Tache;
import Services.EmployeService;
import Services.ProjetService;
import Services.TacheService;

@ManagedBean
@SessionScoped
public class TacheBean {
	@ManagedProperty(value = "#{loginBean}")
	LoginBean loginBean;
	@EJB
	TacheService tacheService;

	@EJB
	ProjetService projetService;
	@EJB
	EmployeService es = new EmployeService();
	private int id_t;
	private String nom_t;
	private String description_t;
	private int dureeEtimee_t;
	private int dureeReelle_t;
	private Date date_debut_t;
	private Date date_fin_t;
	private Boolean isFinished_t;
	private PhaseTache phase_t;

	private String nom_p;
	private String description_p;
	private int dureeEtimee_p;
	private int dureeReelle_p;
	private Date date_debut_p;
	private Date date_fin_p;
	private Boolean isFinished_p;

	private Projet selectedProject;
	private int selected_projet_id;
	private List<Projet> projects;
	private List<Tache> taches;
	private List<Tache> toDoList;
	private List<Tache> doingList;
	private List<Tache> doneList;
	private List<Tache> basketList;

	private List<Tache> taches_projet;
	private List<Tache> mesTaches;
	private Tache selectedTache;
	private int selectedProject_tabBlanc;
	private int projet_id_consulte;

	@PostConstruct
	public void init() {

		taches = tacheService.getAllTaches();
		projects = projetService.getAllProjets();
		if (loginBean.getEmploye() != null)
			mesTaches = tacheService.getMesTaches(loginBean.getEmploye().getId());
		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext extCtx = ctx.getExternalContext();
		Map<String, Object> sessionMap = extCtx.getSessionMap();
		ProjetBean pb = (ProjetBean) sessionMap.get("projetBean");
		if (pb != null) {
			projet_id_consulte = pb.getIdToBeUpdate();
			taches_projet = tacheService.getTachesByProjet(projet_id_consulte);
			System.out.println(projet_id_consulte);
		}

	}

	public void ajouterTache() {

		Tache t = new Tache();
		Projet p = projetService.getProjetById(selected_projet_id);
		System.out.println("eeeeeeeeeeeeeeeeeeeeee" + p);

		t.setNom(nom_t);
		t.setDesc(description_t);
		t.setDate_debut(date_debut_t);
		t.setDureeEtimee(dureeEtimee_t);

		t.setProjet(p);
		t.setPhase(PhaseTache.ToDo);
		t.setIsFinished(false);

		tacheService.ajouter(t);

	}

	public void mAffecter(Tache t) {
		Employe e=new Employe();
		System.out.println(loginBean.getEmploye().getId());
		if (loginBean.getEmploye() != null)
			e = es.getEmployeById(loginBean.getEmploye().getId());
		System.out.println(e);

		t.setEmploye(e);

		tacheService.modifier(t);

	}

	public void modifier() {
		Tache t = new Tache(id_t, nom_t, description_t, dureeEtimee_t, dureeReelle_t, date_debut_t, date_fin_t,
				isFinished_t, phase_t);
		tacheService.modifier(t);
	}

	public void onTacheDrop1_2(DragDropEvent ddEvent) {
		Tache car = ((Tache) ddEvent.getData());

		doingList.add(car);
		toDoList.remove(car);

		car.setPhase(PhaseTache.Doing);
		tacheService.modifier(car);
	}

	public void onTacheDrop2_3(DragDropEvent ddEvent) {
		Tache car = ((Tache) ddEvent.getData());

		doneList.add(car);
		doingList.remove(car);

		car.setPhase(PhaseTache.Done);
		tacheService.modifier(car);
	}

	public void onTacheDrop3_4(DragDropEvent ddEvent) {
		Tache car = ((Tache) ddEvent.getData());

		basketList.add(car);
		doneList.remove(car);

		car.setPhase(null);
		tacheService.modifier(car);
	}

	public void onTacheDrop4_1(DragDropEvent ddEvent) {
		Tache car = ((Tache) ddEvent.getData());

		toDoList.add(car);
		basketList.remove(car);

		car.setPhase(PhaseTache.ToDo);
		tacheService.modifier(car);
	}

	public void afficherTabBlanc() {
		System.out.println(selectedProject_tabBlanc);
		toDoList = tacheService.getToDoTaches(selectedProject_tabBlanc);
		doingList = tacheService.getDoingTaches(selectedProject_tabBlanc);
		doneList = tacheService.getDoneTaches(selectedProject_tabBlanc);
		basketList = tacheService.getBasketTaches(selectedProject_tabBlanc);

	}

	public void supprimer(int id) {
		tacheService.supprimer(id);
	}

	public TacheService getTacheService() {
		return tacheService;
	}

	public void setTacheService(TacheService tacheService) {
		this.tacheService = tacheService;
	}

	public ProjetService getProjetService() {
		return projetService;
	}

	public void setProjetService(ProjetService projetService) {
		this.projetService = projetService;
	}

	public String consulterTache(Tache t) {

		String navigateTo = "null";
		navigateTo = "/resources/pages/manager/consulterTache?faces-redirect=true";

		this.setNom_t(t.getNom());
		this.setDescription_t(t.getDescription());
		this.setDate_debut_t(t.getDate_debut());
		this.setDate_fin_t(t.getDate_fin());
		this.setDureeEtimee_t(t.getDureeEtimee());
		this.setDureeReelle_t(t.getDureeReelle());
		this.setId_t(t.getId());
		this.setIsFinished_t(t.getIsFinished());
		this.setPhase_t(t.getPhase());
		return navigateTo;

	}

	public String getNom_t() {
		return nom_t;
	}

	public int getId_t() {
		return id_t;
	}

	public void setId_t(int id_t) {
		this.id_t = id_t;
	}

	public void setNom_t(String nom_t) {
		this.nom_t = nom_t;
	}

	public String getDescription_t() {
		return description_t;
	}

	public void setDescription_t(String description_t) {
		this.description_t = description_t;
	}

	public int getDureeEtimee_t() {
		return dureeEtimee_t;
	}

	public void setDureeEtimee_t(int dureeEtimee_t) {
		this.dureeEtimee_t = dureeEtimee_t;
	}

	public int getDureeReelle_t() {
		return dureeReelle_t;
	}

	public int getSelected_projet_id() {
		return selected_projet_id;
	}

	public void setSelected_projet_id(int selected_projet_id) {
		this.selected_projet_id = selected_projet_id;
	}

	public void setDureeReelle_t(int dureeReelle_t) {
		this.dureeReelle_t = dureeReelle_t;
	}

	public Date getDate_debut_t() {
		return date_debut_t;
	}

	public void setDate_debut_t(Date date_debut_t) {
		this.date_debut_t = date_debut_t;
	}

	public Date getDate_fin_t() {
		return date_fin_t;
	}

	public void setDate_fin_t(Date date_fin_t) {
		this.date_fin_t = date_fin_t;
	}

	public Boolean getIsFinished_t() {
		return isFinished_t;
	}

	public void setIsFinished_t(Boolean isFinished_t) {
		this.isFinished_t = isFinished_t;
	}

	public String getNom_p() {
		return nom_p;
	}

	public void setNom_p(String nom_p) {
		this.nom_p = nom_p;
	}

	public String getDescription_p() {
		return description_p;
	}

	public void setDescription_p(String description_p) {
		this.description_p = description_p;
	}

	public int getDureeEtimee_p() {
		return dureeEtimee_p;
	}

	public void setDureeEtimee_p(int dureeEtimee_p) {
		this.dureeEtimee_p = dureeEtimee_p;
	}

	public int getDureeReelle_p() {
		return dureeReelle_p;
	}

	public void setDureeReelle_p(int dureeReelle_p) {
		this.dureeReelle_p = dureeReelle_p;
	}

	public Date getDate_debut_p() {
		return date_debut_p;
	}

	public void setDate_debut_p(Date date_debut_p) {
		this.date_debut_p = date_debut_p;
	}

	public Date getDate_fin_p() {
		return date_fin_p;
	}

	public void setDate_fin_p(Date date_fin_p) {
		this.date_fin_p = date_fin_p;
	}

	public Boolean getIsFinished_p() {
		return isFinished_p;
	}

	public void setIsFinished_p(Boolean isFinished_p) {
		this.isFinished_p = isFinished_p;
	}

	public Projet getSelectedProject() {
		return selectedProject;
	}

	public void setSelectedProject(Projet selectedProject) {
		this.selectedProject = selectedProject;
	}

	public List<Projet> getProjects() {
		return projects;
	}

	public void setProjects(List<Projet> projects) {
		this.projects = projects;
	}

	public List<Tache> getTaches() {
		return taches;
	}

	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}

	public List<Tache> getToDoList() {
		return toDoList;
	}

	public void setToDoList(List<Tache> toDoList) {
		this.toDoList = toDoList;
	}

	public List<Tache> getDoingList() {
		return doingList;
	}

	public void setDoingList(List<Tache> doingList) {
		this.doingList = doingList;
	}

	public List<Tache> getDoneList() {
		return doneList;
	}

	public int getSelectedProject_tabBlanc() {
		return selectedProject_tabBlanc;
	}

	public void setSelectedProject_tabBlanc(int selectedProject_tabBlanc) {
		this.selectedProject_tabBlanc = selectedProject_tabBlanc;
	}

	public void setDoneList(List<Tache> doneList) {
		this.doneList = doneList;
	}

	public List<Tache> getBasketList() {
		return basketList;
	}

	public void setBasketList(List<Tache> basketList) {
		this.basketList = basketList;
	}

	public Tache getSelectedTache() {
		return selectedTache;
	}

	public void setSelectedTache(Tache selectedTache) {
		this.selectedTache = selectedTache;
	}

	public List<Tache> getTaches_projet() {

		return taches_projet;
	}

	public void setTaches_projet(List<Tache> taches_projet) {
		this.taches_projet = taches_projet;
	}

	public int getProjet_id_consulte() {
		return projet_id_consulte;
	}

	public void setProjet_id_consulte(int projet_id_consulte) {
		this.projet_id_consulte = projet_id_consulte;
	}

	public PhaseTache getPhase_t() {
		return phase_t;
	}

	public void setPhase_t(PhaseTache phase_t) {
		this.phase_t = phase_t;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public EmployeService getEs() {
		return es;
	}

	public void setEs(EmployeService es) {
		this.es = es;
	}

	public List<Tache> getMesTaches() {
		return mesTaches;
	}

	public void setMesTaches(List<Tache> mesTaches) {
		this.mesTaches = mesTaches;
	}

}
