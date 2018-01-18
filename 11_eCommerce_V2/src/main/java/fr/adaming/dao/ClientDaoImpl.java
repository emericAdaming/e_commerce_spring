package fr.adaming.dao;

import org.hibernate.Query;
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


	@Override
	public Client isExist(Client c) {
		Session s=sf.getCurrentSession();
		String req="SELECT c FROM Client As c where c.nomClient=:pNom AND c.mdp=:pMdp";
		Query q=s.createQuery(req);
		q.setParameter("pNom", c.getNomClient());
		q.setParameter("pMdp", c.getMdp());
		Client clientOut=(Client) q.uniqueResult();
		return clientOut;
	}

}
