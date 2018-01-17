package fr.adaming.managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.modele.Client;
import fr.adaming.modele.Commande;
import fr.adaming.service.CommandeServiceImpl;
import fr.adaming.service.IClientService;
import fr.adaming.service.ICommandeService;
import fr.adaming.service.ILigneCommandeService;

@ManagedBean(name = "commandeMB")
@RequestScoped
public class CommandeManagedBean {

	@ManagedProperty(value="#{coService}")
	ICommandeService commandeService;

	@ManagedProperty(value="#{lcService}")
	ILigneCommandeService ligneCommandeService;

	@ManagedProperty(value="#{clService}")
	IClientService clientService;

	private Commande commande;
	private Client client;

	private HttpSession maSession;

	// Constructeur

	public CommandeManagedBean() {
		this.commande = new Commande();
		this.client = new Client();
	}

	@PostConstruct
	public void init() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

	}

	// Setter pour l'injection dépendance
	public void setCommandeService(ICommandeService commandeService) {
		this.commandeService = commandeService;
	}

	public void setLigneCommandeService(ILigneCommandeService ligneCommandeService) {
		this.ligneCommandeService = ligneCommandeService;
	}

	public void setClientService(IClientService clientService) {
		this.clientService = clientService;
	}
	
	// Getters & Setters


	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	// Méthodes metiers

	public String ajouterCommande() {
		System.out.println("********************AJOUTER COMMANDE*************");

		// Ajouter le client
		this.client = clientService.ajouterClient(this.client);

		commande.setClient(this.client);

		// Ajouter la date
		String format = "dd/MM/yy H:mm:ss";
		java.text.SimpleDateFormat formater = new java.text.SimpleDateFormat(format);
		java.util.Date date = new java.util.Date();

		commande.setDateCommande(date);
		// Attribuer le N° de la commande aux lignes de commandes

		System.out.println("********************" + maSession.getAttribute("listeLignes"));

		List<LigneCommande> liste = (List<LigneCommande>) maSession.getAttribute("listeLignes");

		// Ajouter la commande à la BDD
		this.commande = commandeService.ajouterCommande(this.commande);

		for (LigneCommande ligneCommande : liste) {
			ligneCommande.setCommande(this.commande);
			ligneCommandeService.updateLigneCommande(ligneCommande);
		}
		//Envoie des informations dans session pour que envoieMail de clientMB possede toutes les infos necessaires (montant,email ...)
		maSession.setAttribute("client", this.client);
		
		return "commande";
	}
	
	
	


}
