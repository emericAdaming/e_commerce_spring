package fr.adaming.managedBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import javax.faces.context.FacesContext;

import fr.adaming.modele.Categorie;
import fr.adaming.modele.LigneCommande;
import fr.adaming.modele.Produit;
import fr.adaming.service.ICategorieService;
import fr.adaming.service.ILigneCommandeService;
import fr.adaming.service.IProduitService;

@ManagedBean(name = "produitMB")
@SessionScoped
public class ProduitManagedBean implements Serializable {

	@ManagedProperty(value="#{pService}")
	private IProduitService produitService;

	@ManagedProperty(value="#{caService}")
	private ICategorieService categorieService;

	@ManagedProperty(value="#{lcService}")
	private ILigneCommandeService ligneCommandeService;

	// Attributs
	
	private Produit produit;

	private List<Produit> listeProduits;

	private Categorie categorie;

	private String image;

	private LigneCommande ligne;

	private HttpSession maSession;

	// Constructeur

	public ProduitManagedBean() {
		this.produit = new Produit();
		this.categorie = new Categorie();
	}

	@PostConstruct
	public void init() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

	}

	// Setter pour l'injection d�pendance
	public void setCategorieService(ICategorieService categorieService) {
		this.categorieService = categorieService;
	}

	public void setLigneCommandeService(ILigneCommandeService ligneCommandeService) {
		this.ligneCommandeService = ligneCommandeService;
	}
	
	public void setProduitService(IProduitService produitService) {
		this.produitService = produitService;
	}
	
	// Getters & Setters


	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public LigneCommande getLigne() {
		return ligne;
	}

	public void setLigne(LigneCommande ligne) {
		this.ligne = ligne;
	}

	// M�thodes m�tier

	public String getProduitsCategorie() {

		System.out.println("Categorie du produit: " + this.categorie);
		System.out.println(" %%%%%%%%%%%%%%% Categorie du produit: " + this.categorie.getIdCategorie()
				+ "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
		List<Produit> listOut = produitService.getProduitsCategorie(this.categorie);
		this.listeProduits = new ArrayList<Produit>();

		// On ajoute la liste de produits dans la session
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeProduits", listeProduits);
		for (Produit element : listOut) {
			if (element.getPhoto() == null) {
				element.setImage(null);
			} else {
				element.setImage("data:image/png;base64," + Base64.encodeBase64String(element.getPhoto()));
			}
			this.listeProduits.add(element);
		}

		return "produits";

	}

	public String getProduitsSelect() {
		System.out.println("Acces panier");
		this.listeProduits = produitService.getProduitsSelect();
		listeProduits.forEach(System.out::println);

		return "panier";

	}

	public void upload(FileUploadEvent event) {
		UploadedFile uploadedFile = event.getFile();
		byte[] contents = uploadedFile.getContents();
		produit.setPhoto(contents);
		image = "data:image/png;base64," + Base64.encodeBase64String(contents);
	}

	public String ajouterProduit() {
		System.out.println("Enregistrement Produit");

		// R�cup�rer la categorie a partir du nom

		Categorie categorieByName = categorieService.getCategorieByName(this.categorie);

		// Attribuer la cat�gorie au produit � ajouter
		this.produit.setCategorie(categorieByName);

		// Ajouter le produit
		produitService.addProduit(this.produit);

		// R�cup�rer la nouvelle liste � partir de la BDD
		 this.listeProduits= produitService.getProduitsCategorie(categorieByName);
		 
		
		// Metre � jour la liste dans la session
		maSession.setAttribute("listeProduits", this.listeProduits);

		produit = new Produit();
		image = "";
		return "accueil";
	}

	public String supprimerProduit() {
		// R�cup�rer la ligne de commande a supprimer
		try {
			System.out.println("***************SUPPRIMER PRODUIT****************");
			System.out.println(this.produit);
			System.out.println("************ID PRODUIT********" + produit.getIdProduit());
			this.ligne = ligneCommandeService.getLigneByIdProduit(produit.getIdProduit());
			System.out.println("********LIGNE A SUPPRIMER*******" + this.ligne);

			// Supprimer la ligne de commande
			ligneCommandeService.supprimerLigneCommande(ligne);

			System.out.println("PRODUIT EXISTANT DANS LIGNECOMMANDE");
			
			// Supprimer le produit
			produitService.deleteProduit(this.produit);

			System.out.println(this.categorie);

			// R�cup�rer la nouvelle liste � partir de la BDD
			
			List<Produit> listOut= produitService.getProduitsCategorie(this.categorie);
			
		
			// Metre � jour la liste dans la session
			maSession.setAttribute("listeProduits", listOut);

			produit = new Produit();
			image = "";
			return "produits";
		} catch (Exception e) {
			System.out.println("PRODUIT INEXISTANT DANS LIGNECOMMANDE");
			
			System.out.println("*******LISTE PRODUITS AVANT SUPPRESSION*********"+maSession.getAttribute("listeProduits"));
			
			
			// Supprimer le produit
			produitService.deleteProduit(this.produit);

			System.out.println(this.categorie);

			// R�cup�rer la nouvelle liste � partir de la BDD
			List<Produit> listOut= produitService.getProduitsCategorie(this.categorie);
			this.listeProduits.clear();
			for (Produit element : listOut) {
				if (element.getPhoto() == null) {
					element.setImage(null);
				} else {
					element.setImage("data:image/png;base64," + Base64.encodeBase64String(element.getPhoto()));
				}
				this.listeProduits.add(element);
			}

			// Metre � jour la liste dans la session
			maSession.setAttribute("listeProduits", listOut);

			System.out.println("********MB listOut********"+listOut);
			
			System.out.println("********MB session liste********"+maSession.getAttribute("listeProduits"));
			
			produit = new Produit();
			image = "";
			return "produits";
		}

	}

	public String updateProduit() {

		System.out.println("***************UPDATE******************");

		System.out.println("**************PRODUIT A MODIFIER**********" + this.produit);

		// R�cup�rer la categorie a partir du nom

		System.out.println("***************CATEGORIE BY NOM******************" + this.categorie);
		Categorie categorieByName = categorieService.getCategorieByName(this.categorie);

		// Attribuer la cat�gorie au produit � ajouter
		this.produit.setCategorie(categorieByName);

		// Modifier le produit
		produitService.updateProduit(this.produit);

		// R�cup�rer la nouvelle liste � partir de la BDD
		this.listeProduits = produitService.getProduitsCategorie(categorieByName);

		// Metre � jour la liste dans la session
		maSession.setAttribute("listeProduits", this.listeProduits);

		produit = new Produit();
		image = "";
		return "produits";
	}
}
