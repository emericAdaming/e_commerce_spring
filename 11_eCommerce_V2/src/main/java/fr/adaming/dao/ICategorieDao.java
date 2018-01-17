package fr.adaming.dao;

import java.util.List;

import fr.adaming.modele.Categorie;

public interface ICategorieDao {

	public List<Categorie> getAllCategories();
	
	public Categorie addCategorie(Categorie c);
	
	public byte[] getCategorieById(int id_categorie);
	
	public Categorie getCategorieByName(Categorie c);
	
	public void deleteCategorie(Categorie c);
	
	public Categorie updateCategorie(Categorie c);

}
