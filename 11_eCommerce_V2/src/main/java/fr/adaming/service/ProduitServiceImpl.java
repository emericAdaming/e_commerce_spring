package fr.adaming.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateful;

import fr.adaming.dao.Categorie;
import fr.adaming.dao.IProduitDao;
import fr.adaming.dao.Produit;

@Stateful
public class ProduitServiceImpl implements IProduitService{

	@EJB
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
