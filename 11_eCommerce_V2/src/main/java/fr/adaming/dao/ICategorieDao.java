package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.dao.Categorie;

@Local
public interface ICategorieDao {

	public List<Categorie> getAllCategories();
	
	public Categorie addCategorie(Categorie c);
	
	public byte[] getCategorieById(int id_categorie);
	
	public Categorie getCategorieByName(Categorie c);
	
	public void deleteCategorie(Categorie c);
	
	public Categorie updateCategorie(Categorie c);

}
