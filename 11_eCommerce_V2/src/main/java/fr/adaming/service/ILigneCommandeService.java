package fr.adaming.service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.dao.LigneCommande;

@Local
public interface ILigneCommandeService {

	public LigneCommande ajouterLigneCommande(LigneCommande ligne);
	
	public List<LigneCommande> getAllLignes();
	
	public LigneCommande isExistLigneCommande(LigneCommande ligne) throws Exception;
	
	public LigneCommande updateLigneQte(LigneCommande ligne);
	
	public void supprimerLigneCommande(LigneCommande ligne);
	
	public LigneCommande quantiteLigneCommande(LigneCommande ligne);
	
	public int totalPanier(List<LigneCommande> listeLignes);
	
	public LigneCommande updateLigneCommande(LigneCommande ligne);
	
	public LigneCommande getLigneByIdProduit(long idProduit) throws Exception;
	
}
