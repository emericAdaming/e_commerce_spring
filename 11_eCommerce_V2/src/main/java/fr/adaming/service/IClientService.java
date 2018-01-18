package fr.adaming.service;

import fr.adaming.modele.Client;

public interface IClientService {

	public Client ajouterClient(Client c);
	
	public Client isExist(Client c);
	
}
