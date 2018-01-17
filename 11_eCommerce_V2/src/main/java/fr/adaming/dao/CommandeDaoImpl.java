package fr.adaming.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.modele.Commande;

@Repository
public class CommandeDaoImpl implements ICommandeDao{

	@Autowired //injection du collaborateur sf
	private SessionFactory sf;
		
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	
	@Override
	public Commande ajouterCommande(Commande c) {
		Session s=sf.getCurrentSession();
		// Ajouter la commande a la BDD
		s.save(c);
		return c;
	}
	


}
