package fr.adaming.dao;

import java.util.List;

import fr.adaming.modele.Categorie;
import fr.adaming.modele.Produit;

public interface IProduitDao {

	public List<Produit> getProduitsCategorie(Categorie c);
	
	public List<Produit> getProduitsSelect();
	
	public Produit addProduit(Produit p);
	
	public void deleteProduit(Produit p);
	
	public Produit updateProduit(Produit p);
	
}
