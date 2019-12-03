package tn.esprit.service;

import static org.opencv.imgproc.Imgproc.COLOR_BGR2GRAY;
import static org.opencv.imgproc.Imgproc.cvtColor;

import java.io.File;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import tn.esprit.entity.Employe;
import tn.esprit.entity.Role;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Stateless
@LocalBean
public class Serviceemp implements Serviceempremote {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Employe getemployeemailpass(String mail, String Pass) {

		TypedQuery<Employe> query = (TypedQuery<Employe>) em
				.createQuery("Select e from Employe e where e.email = :mail and e.Password = :pass", Employe.class);
		query.setParameter("mail", mail);
		query.setParameter("pass", Pass);

		Employe employe = null;

		try {

			employe = query.getSingleResult();
		} catch (Exception e) {
		}

		return employe;

	}

	@Override
	public List<Employe> getAllEmployes() {
		List<Employe> emp = em.createQuery("Select e from Employe e", Employe.class).getResultList();
		return emp;
	}

	@Override
	public int Addemploye(Employe e) {

		String pass = "";
		String chars = "abcdefghijklmno1234567890";

		for (int x = 0; x < 25; x++) {
			int i = (int) Math.floor(Math.random() * 25);
			pass += chars.charAt(i);
		}
		String code = "advyteam" + pass;
		e.setCodeqr(code);
		System.out.println("Start recognize text from image");
		long start = System.currentTimeMillis();
		e.setQrlogin("www.esprit.tn");
		System.out.println("Time");
		System.out.println(System.currentTimeMillis() - start);
		System.out.println("Done");
		em.persist(e);
		return e.getId();

	}

	public void updateEmploye(Employe employe) {
		Employe emp = em.find(Employe.class, employe.getId());
		emp.setPrenom(employe.getPrenom());
		emp.setNom(employe.getNom());
		emp.setEmail(employe.getEmail());
		emp.setDatedn(emp.getDatedn());
		emp.setPassword(employe.getPassword());
		emp.setRole(employe.getRole());
		emp.setImage(employe.getImage());
		emp.setNumtel(employe.getNumtel());
		emp.setQrlogin(employe.getQrlogin());
		em.merge(emp);

	}

	public void deleteEmployeById(int id) {

		em.createQuery("delete From Employe e where e.id=:id").setParameter("id", id).executeUpdate();

	}

	@Override
	public int AddemployeBeta(Role role, String image) {

		nu.pattern.OpenCV.loadShared();

		String result = new testjava().extractTextFromImage(Imgcodecs.imread("C:/Recognize/" + image));
		System.out.println(result);

		System.out.println("Time");
		System.out.println(System.currentTimeMillis());
		System.out.println("Done");
		Employe e = new Employe();
		String[] parts = result.split("\n");

		System.out.println(parts[0]);

		String[] CIN = parts[0].split(":");

		System.out.println(CIN[1]);

		String[] NOM = parts[1].split(":");

		System.out.println(NOM[1]);

		String[] PRENOM = parts[2].split(":");

		System.out.println(PRENOM[1]);

		String[] DATED = parts[3].split(":");

		System.out.println(DATED[1]);
		DateFormat formatter1 = new SimpleDateFormat("dd-MMM-yyyy");

		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

		try {
			Date date = formatter.parse(DATED[1]);
			e.setDatedn(date);

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		e.setNom(NOM[1]);
		e.setPrenom(PRENOM[1]);
		e.setPassword(CIN[1]);
		e.setEmail(NOM[1] + "." + PRENOM[1] + "@advyteam.tn");
		e.setRole(role);

		return Addemploye(e);

	}

	@Override
	public Employe getemployemail(String Mail) {
		TypedQuery<Employe> query = (TypedQuery<Employe>) em
				.createQuery("Select e from Employe e where e.email = :mail ", Employe.class);
		query.setParameter("mail", Mail);

		Employe employe = null;

		try {

			employe = query.getSingleResult();
		} catch (Exception e) {
		}

		return employe;

	}

	@Override
	public Employe getemployemailqr(String Mail, String Qr) {
		TypedQuery<Employe> query = (TypedQuery<Employe>) em
				.createQuery("Select e from Employe e where e.email = :mail and e.codeqr = :pass", Employe.class);
		query.setParameter("mail", Mail);
		query.setParameter("pass", Qr);

		Employe employe = null;

		try {

			employe = query.getSingleResult();
		} catch (Exception e) {
		}

		return employe;

	}

	@Override
	public void updatere(String Mail, String Qr, String pass) {

		String passe = "";
		String chars = "abcdefghijklmno1234567890";

		for (int x = 0; x < 25; x++) {
			int i = (int) Math.floor(Math.random() * 25);
			passe += chars.charAt(i);
		}
		String code = "advyteam" + passe;
		String qr = code;
		Query query = em.createQuery(
				"Update Employe e SET e.codeqr=:Depnom2 , e.Password=:pass where e.email=:mail and e.codeqr=:oldqr");
		query.setParameter("Depnom2", qr);
		query.setParameter("pass", pass);
		query.setParameter("mail", Mail);
		query.setParameter("oldqr", Qr);
		query.executeUpdate();
	}

	public List<Employe> rechercherEmployeParCritere(String critere) {
		// TODO Auto-generated method stub
		TypedQuery<Employe> query = em.createQuery("select P from Employe P where P.nom LIKE :critere OR "
				+ "P.prenom LIKE :critere OR " + "P.id LIKE :critere OR " + "P.role LIKE :critere OR "
				+ "P.datedn LIKE :critere OR " + "P.email LIKE :critere", Employe.class);
		query.setParameter("critere", "%" + critere + "%");
		List<Employe> listPers = query.getResultList();
		return listPers;
	}

}
