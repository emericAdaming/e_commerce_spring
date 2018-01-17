package fr.adaming.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.modele.LigneCommande;



@Service("lcService") // Déclarer la classe comme un bean (Service)
@Transactional // Rendre toutes les méthodes transactionnables
public class LigneCommandeServiceImpl implements ILigneCommandeService{

	@Autowired
	ILigneCommandeDao ligneCommandeDao;
	
	// Setter pour l'injection dépendance
	public void setLigneCommandeDao(ILigneCommandeDao ligneCommandeDao) {
		this.ligneCommandeDao = ligneCommandeDao;
	}

	@Override
	public LigneCommande ajouterLigneCommande(LigneCommande ligne) {
		// TODO Auto-generated method stub
		return ligneCommandeDao.ajouterLigneCommande(ligne);
	}

	@Override
	public List<LigneCommande> getAllLignes() {
		// TODO Auto-generated method stub
		return ligneCommandeDao.getAllLignes();
	}

	@Override
	public LigneCommande isExistLigneCommande(LigneCommande ligne) throws Exception {
		// TODO Auto-generated method stub
		return ligneCommandeDao.isExistLigneCommande(ligne);
	}

	@Override
	public LigneCommande updateLigneQte(LigneCommande ligne) {
		// TODO Auto-generated method stub
		return ligneCommandeDao.updateLigneQte(ligne);
	}

	@Override
	public void supprimerLigneCommande(LigneCommande ligne) {
		// TODO Auto-generated method stub
		ligneCommandeDao.supprimerLigneCommande(ligne);
	}

	@Override
	public LigneCommande quantiteLigneCommande(LigneCommande ligne) {
		// TODO Auto-generated method stub
		return ligneCommandeDao.quantiteLigneCommande(ligne);
	}

	@Override
	public int totalPanier(List<LigneCommande> listeLignes) {
		// TODO Auto-generated method stub
		return ligneCommandeDao.totalPanier(listeLignes);
	}

	@Override
	public LigneCommande updateLigneCommande(LigneCommande ligne) {
		// TODO Auto-generated method stub
		return ligneCommandeDao.updateLigneCommande(ligne);
	}

	@Override
	public LigneCommande getLigneByIdProduit(long idProduit) throws Exception {
		// TODO Auto-generated method stub
		return ligneCommandeDao.getLigneByIdProduit(idProduit);
	}

	
	
}
