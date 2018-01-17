package fr.adaming.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.ICommandeDao;
import fr.adaming.modele.Commande;

@Service("coService") // Déclarer la classe comme un bean (Service)
@Transactional // Rendre toutes les méthodes transactionnables
public class CommandeServiceImpl implements ICommandeService{

	@Autowired
	ICommandeDao commandeDao;
	
	//Setter pour l'injection dépendance
	public void setCommandeDao(ICommandeDao commandeDao) {
		this.commandeDao = commandeDao;
	}

	@Override
	public Commande ajouterCommande(Commande c) {
		// TODO Auto-generated method stub
		return commandeDao.ajouterCommande(c);
	}

}
