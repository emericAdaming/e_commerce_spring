package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.modele.Categorie;



@Repository
public class CategorieDaoImpl implements ICategorieDao {
	
	@Autowired 
	private SessionFactory sf;
		
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}
	

	@Override
	public List<Categorie> getAllCategories() {
		Session s=sf.getCurrentSession();
		// Ecrire la requete 
		String req="SELECT c FROM Categorie c";
		
		// On crée la query
		Query query=s.createQuery(req);
		
		// Envoyer la requete et recuperer la liste
		List<Categorie> liste=query.list();
		
		return liste;
	}
	
	public Categorie addCategorie(Categorie c){
		Session s=sf.getCurrentSession();	
		s.save(c);
		return c;
	}


	@Override
	public byte[] getCategorieById(int id_categorie) {
		Session s=sf.getCurrentSession();
		System.out.println("********** Recuperer categorie by id ****************");
		Categorie c;
		try{
		byte[] p;
		// Ecrire la requete 
		String req="SELECT c FROM Categorie c where c.idCategorie=:pId";			
		Query query=s.createQuery(req);
		query.setParameter("pId", (long)id_categorie);
				
		// Envoyer la requete et recuperer la liste
		 c=(Categorie) query.uniqueResult();	 
		 return c.getPhoto();
		 
		}catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("********** Retourne byte array ****************");
		return null;
	}


	@Override
	public Categorie getCategorieByName(Categorie c) {
		Session s=sf.getCurrentSession();
		
		// Ecrire la requete
		String req="SELECT c FROM Categorie AS c WHERE c.nomCategorie=:pNomCategorie";
		
		// On crée la query
		Query query=s.createQuery(req);
		
		// PAramètres
		query.setParameter("pNomCategorie", c.getNomCategorie());
		
		// Envoyer la requete et récupérer le résultat
		Categorie categorieOut=(Categorie) query.uniqueResult();
		
		return categorieOut;
	}

	@Override
	public void deleteCategorie(Categorie c){
		Session s=sf.getCurrentSession();
		// Ecrire la requete
		String req = "DELETE FROM Categorie AS c WHERE c.idCategorie=:pIdCategorie";

		// Query
		Query query =s.createQuery(req);

		// Definir les paramètres
		query.setParameter("pIdCategorie", c.getIdCategorie());

		// Executer la requete
		query.executeUpdate();

	}


	@Override
	public Categorie updateCategorie(Categorie c) {
		Session s=sf.getCurrentSession();
		// Trouver la categorie à modifier
		Categorie categorieOut=(Categorie) s.get(Categorie.class, c.getIdCategorie());
		categorieOut.setDescription(c.getDescription());
		categorieOut.setNomCategorie(c.getNomCategorie());
		categorieOut.setPhoto(c.getPhoto());
		
		s.saveOrUpdate(categorieOut);
		
		return categorieOut;
	}
	
	
}
