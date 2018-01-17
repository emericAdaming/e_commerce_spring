package fr.adaming.managedBeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.modele.Admin;
import fr.adaming.service.IAdminService;

@ManagedBean(name = "adminMB")
@RequestScoped
public class AdminManagedBean implements Serializable {

	@ManagedProperty(value="#{aService}")
	private IAdminService adminService;

	private Admin admin;
	
	private HttpSession maSession;

	// Constructeur

	public AdminManagedBean() {
		this.admin = new Admin();
	}

	// Setter pour l'injection dépendance (OBLIGATOIRE !!)
	public void setAdminService(IAdminService adminService) {
		this.adminService = adminService;
	}
	
	// Getters & Setters

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}


	@PostConstruct
	public void init() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

	}
	// Méthodes métier

	public String seConnecter() {

		Admin adminOut;
		try {
			adminOut = adminService.isExist(this.admin);

			// Ajouter l'admin dans la session
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminSession", adminOut);
			
			return "accueil";

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "login";
		}

	}
	
	public String seDeconnecter() {
		System.out.println("Deconnection !!!!!!!!!!!!!");
		
		this.admin=null;
		//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("adminSession", null);
		// Metre à jour la liste dans la session
		maSession.setAttribute("adminSession", null);
		System.out.println("Admin session="+maSession.getAttribute("adminSession"));		
		return "deconnection";
	}
	
	public String goConnection(){
		return "login";
	}

}

