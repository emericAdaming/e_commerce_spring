package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateful;

import fr.adaming.dao.Categorie;
import fr.adaming.dao.ICategorieDao;

@Stateful
public class CategorieServiceImpl implements ICategorieService{

	@EJB
	ICategorieDao categorieDao;
	
	@Override
	public List<Categorie> getAllCategories() {
		// TODO Auto-generated method stub
		return categorieDao.getAllCategories();
	}

	public Categorie addCategorie(Categorie c){
		
		return categorieDao.addCategorie(c);
	}

	@Override
	public byte[] getCategorieById(int id_categorie) {

		return categorieDao.getCategorieById(id_categorie);
	}

	@Override
	public Categorie getCategorieByName(Categorie c) {
		// TODO Auto-generated method stub
		return categorieDao.getCategorieByName(c);
	}

	@Override
	public void deleteCategorie(Categorie c) {
		// TODO Auto-generated method stub
		categorieDao.deleteCategorie(c);
	}

	@Override
	public Categorie updateCategorie(Categorie c) {
		// TODO Auto-generated method stub
		return categorieDao.updateCategorie(c);
	}
}
