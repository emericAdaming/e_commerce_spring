package fr.adaming.service;

import java.util.List;

import fr.adaming.modele.Categorie;

public interface ICategorieService {
	
	public List<Categorie> getAllCategories();
	
	public Categorie addCategorie(Categorie c);
	
	public byte[] getCategorieById(int id_categorie);

	public Categorie getCategorieByName(Categorie c);
	
	public void deleteCategorie(Categorie c);
	
	public Categorie updateCategorie(Categorie c);
	
}
