package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.dao.Client;

@Local
public interface IClientService {

	public Client ajouterClient(Client c);
	
}
