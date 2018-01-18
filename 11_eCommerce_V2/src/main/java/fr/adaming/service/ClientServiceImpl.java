package fr.adaming.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IClientDao;
import fr.adaming.modele.Client;

@Service("clService") // D�clarer la classe comme un bean (Service)
@Transactional // Rendre toutes les m�thodes transactionnables
public class ClientServiceImpl implements IClientService{

	@Autowired
	private IClientDao clientDao;
	
	// Setter pour l'injection d�pendance (pas obligatoire ici)
	public void setClientDao(IClientDao clientDao) {
		this.clientDao = clientDao;
	}
	
	@Override
	public Client ajouterClient(Client c) {
		// TODO Auto-generated method stub
		return clientDao.ajouterClient(c);
	}

	@Override
	public Client isExist(Client c) {
	
		return clientDao.isExist(c);
	}


}
