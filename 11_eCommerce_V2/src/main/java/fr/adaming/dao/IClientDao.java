package fr.adaming.dao;

import fr.adaming.modele.Client;

public interface IClientDao {

	public Client ajouterClient(Client c);
	
	public Client isExist(Client c);
	
}
