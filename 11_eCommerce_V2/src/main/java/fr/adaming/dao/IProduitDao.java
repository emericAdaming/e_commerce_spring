package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.dao.Categorie;
import fr.adaming.dao.Produit;

@Local
public interface IProduitDao {

	public List<Produit> getProduitsCategorie(Categorie c);
	
	public List<Produit> getProduitsSelect();
	
	public Produit addProduit(Produit p);
	
	public void deleteProduit(Produit p);
	
	public Produit updateProduit(Produit p);
	
}
