package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.dao.Categorie;
import fr.adaming.dao.Produit;

@Stateless
public class CategorieDaoImpl implements ICategorieDao {
	
	@PersistenceContext(unitName="eCommerce_V1")
	private EntityManager em;

	// Setter pour l'entity Manager
	public void setEm(EntityManager em) {
		this.em = em;
	}
	

	@Override
	public List<Categorie> getAllCategories() {
		
		// Ecrire la requete 
		String req="SELECT c FROM Categorie c";
		
		// On crée la query
		Query query=em.createQuery(req);
		
		// Envoyer la requete et recuperer la liste
		List<Categorie> liste=query.getResultList();
		
		return liste;
	}
	
	public Categorie addCategorie(Categorie c){
			
		em.persist(c);
		return c;
	}


	@Override
	public byte[] getCategorieById(int id_categorie) {
		System.out.println("********** Recuperer categorie by id ****************");
		Categorie c;
		try{
		byte[] p;
		// Ecrire la requete 
		String req="SELECT c FROM Categorie c where c.idCategorie=:pId";			
		Query query=em.createQuery(req);
		query.setParameter("pId", (long)id_categorie);
				
		// Envoyer la requete et recuperer la liste
		 c=(Categorie) query.getSingleResult();		 
		 return c.getPhoto();
		 
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		}
		
		System.out.println("********** Retourne byte array ****************");
		return null;
	}


	@Override
	public Categorie getCategorieByName(Categorie c) {
		
		// Ecrire la requete
		String req="SELECT c FROM Categorie AS c WHERE c.nomCategorie=:pNomCategorie";
		
		// On crée la query
		Query query=em.createQuery(req);
		
		// PAramètres
		query.setParameter("pNomCategorie", c.getNomCategorie());
		
		// Envoyer la requete et récupérer le résultat
		Categorie categorieOut=(Categorie) query.getSingleResult();
		
		return categorieOut;
	}

	@Override
	public void deleteCategorie(Categorie c){
	
	// Ecrire la requete
			String req = "DELETE FROM Categorie AS c WHERE c.idCategorie=:pIdCategorie";

			// Query
			Query query = em.createQuery(req);

			// Definir les paramètres
			query.setParameter("pIdCategorie", c.getIdCategorie());

			// Executer la requete
			query.executeUpdate();

	}


	@Override
	public Categorie updateCategorie(Categorie c) {
		
		// Trouver la categorie à modifier
		Categorie categorieOut=em.find(Categorie.class, c.getIdCategorie());
		categorieOut.setDescription(c.getDescription());
		categorieOut.setNomCategorie(c.getNomCategorie());
		categorieOut.setPhoto(c.getPhoto());
		
		em.merge(categorieOut);
		
		return categorieOut;
	}
	
	
}
