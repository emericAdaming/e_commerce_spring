package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.dao.Commande;

@Local
public interface ICommandeService {

	public Commande ajouterCommande(Commande c);
	
}
