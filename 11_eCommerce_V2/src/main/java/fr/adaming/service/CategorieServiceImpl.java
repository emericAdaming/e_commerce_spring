package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICategorieDao;
import fr.adaming.modele.Categorie;

@Service("caService") // Déclarer la classe comme un bean (Service)
@Transactional // Rendre toutes les méthodes transactionnables
public class CategorieServiceImpl implements ICategorieService{

	@Autowired
	ICategorieDao categorieDao;
	
	// Setter pour l'injection dépendance (pas obligatoire ici)
	public void setCategorieDao(ICategorieDao categorieDao) {
		this.categorieDao = categorieDao;
	}
	
	// Méthodes
	
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
