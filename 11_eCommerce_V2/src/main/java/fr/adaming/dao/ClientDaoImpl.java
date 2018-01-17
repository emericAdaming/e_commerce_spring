package fr.adaming.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.modele.Client;

@Repository
public class ClientDaoImpl implements IClientDao {


	
	@Autowired //injection du collaborateur sf
	private SessionFactory sf;
	
	
	// pas obligatoire
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}



	@Override
	public Client ajouterClient(Client c) {
		Session s=sf.getCurrentSession();
		// Ajouter un client à la BDD
		s.save(c); // Le client rentré est sans ID
		
		return c; // Le client retourné est avec ID
	}

}
