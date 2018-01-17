package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IProduitDao;
import fr.adaming.modele.Categorie;
import fr.adaming.modele.Produit;

@Service("pService") // Déclarer la classe comme un bean (Service)
@Transactional // Rendre toutes les méthodes transactionnables
public class ProduitServiceImpl implements IProduitService{

	@Autowired
	IProduitDao produitDao;

	@Override
	public List<Produit> getProduitsCategorie(Categorie c) {
		// TODO Auto-generated method stub
		return produitDao.getProduitsCategorie(c);
	}

	@Override
	public List<Produit> getProduitsSelect() {
		// TODO Auto-generated method stub
		return produitDao.getProduitsSelect();
	}

	@Override
	public Produit addProduit(Produit p) {
		// TODO Auto-generated method stub
		return produitDao.addProduit(p);
	}

	@Override
	public void deleteProduit(Produit p) {

		produitDao.deleteProduit(p);
		
	}

	@Override
	public Produit updateProduit(Produit p) {
		// TODO Auto-generated method stub
		return produitDao.updateProduit(p);
	}
	
}
