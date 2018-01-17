package fr.adaming.dao;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.adaming.dao.Commande;

@Stateful
public class CommandeDaoImpl implements ICommandeDao{

	@PersistenceContext(unitName="eCommerce_V1")
	private EntityManager em;
	
	// Setter pour l'injection de la dépendance
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Commande ajouterCommande(Commande c) {
		// Ajouter la commande a la BDD
		em.persist(c);
		return c;
	}
	


}
