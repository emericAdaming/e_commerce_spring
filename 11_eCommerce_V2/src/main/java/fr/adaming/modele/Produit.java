package fr.adaming.modele;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="produits")
public class Produit {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idProduit;
	private String description;
	private String designation;
	private double prix;
	private int quantite;
	private int reduction;
	private boolean selectionne;
	@Transient
	private int quantiteSelect;
	@Lob
	private byte[] photo;
	@Transient
	private String image;
	
	@OneToMany(mappedBy="produit",cascade=CascadeType.ALL)
	private List<LigneCommande> ligneCommande;
	@ManyToOne
	@JoinColumn(name="id_categorie",referencedColumnName="idCategorie")
	private  Categorie categorie;
	
	
	// Constructeurs
	
	public Produit() {
		super();
	}


	public Produit(Long idProduit, String description, String designation, double prix, int quantite,
			boolean selectionne, byte[] photo,int quantiteSelect,int reduction) {
		super();
		this.idProduit = idProduit;
		this.description = description;
		this.designation = designation;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.photo = photo;
		this.quantiteSelect=quantiteSelect;
		this.reduction=reduction;
	}


	public Produit(String description, String designation, double prix, int quantite, boolean selectionne,
			byte[] photo,Categorie categorie,int quantiteSelect,int reduction) {
		super();
		this.description = description;
		this.designation = designation;
		this.prix = prix;
		this.quantite = quantite;
		this.selectionne = selectionne;
		this.photo = photo;
		this.categorie = categorie;
		this.quantiteSelect=quantiteSelect;
		this.reduction=reduction;
	}


	// Getters & Setters
	
	public Long getIdProduit() {
		return idProduit;
	}

	public void setIdProduit(Long idProduit) {
		this.idProduit = idProduit;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public boolean isSelectionne() {
		return selectionne;
	}

	public void setSelectionne(boolean selectionne) {
		this.selectionne = selectionne;
	}

	
	public byte[] getPhoto() {
		return photo;
	}


	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	
	

	public Categorie getCategorie() {
		return categorie;
	}


	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}


	public List<LigneCommande> getLigneCommande() {
		return ligneCommande;
	}


	public void setLigneCommande(List<LigneCommande> ligneCommande) {
		this.ligneCommande = ligneCommande;
	}
	
	
	
	public int getQuantiteSelect() {
		return quantiteSelect;
	}


	public void setQuantiteSelect(int quantiteSelect) {
		this.quantiteSelect = quantiteSelect;
	}
	
	


	public int getReduction() {
		return reduction;
	}


	public void setReduction(int reduction) {
		this.reduction = reduction;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
		this.image = image;
	}
	
	

//************ 	// toString **********************************************************




	@Override
	public String toString() {
		return "Produit [idProduit=" + idProduit + ", description=" + description + ", designation=" + designation
				+ ", prix=" + prix + ", quantite=" + quantite + ", selectionne=" + selectionne + ", photo=" + photo
				+ "]";
	}
	
	
	
	

}
