package fr.adaming.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.adaming.modele.Categorie;
import fr.adaming.modele.Produit;

@Repository
public class ProduitDaoImpl implements IProduitDao {

	@Autowired //injection du collaborateur sf
	private SessionFactory sf;
		
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public List<Produit> getProduitsCategorie(Categorie c) {
		Session s=sf.getCurrentSession();
		// Ecrire la requête
		String req = "SELECT p FROM Produit p WHERE p.categorie=:pCategorie";

		// Création du query
		Query query = s.createQuery(req);

		// Definition des parametres
		query.setParameter("pCategorie", c);

		// Envoie de la requete et récupération de la liste
		List<Produit> p_list=query.list();	
		for(Produit element:p_list){
			System.out.println("Element trouve :"+element);
		}
		if(p_list==null)
			System.out.println("Aucun produit trouve");

		System.out.println("******DAO******"+p_list);
		return p_list;
	}

	@Override
	public List<Produit> getProduitsSelect() {
		Session s=sf.getCurrentSession();
		// Ecrire la requête
		String req = "SELECT p FROM Produit p WHERE p.selectionne=true";

		// Création du query
		Query query = s.createQuery(req);

		// Envoie de la requete et récupération de la liste
		
		return query.list();
	}

	@Override
	public Produit addProduit(Produit p) {
		Session s=sf.getCurrentSession();
		// Ajouter le produit à la BDD
		s.save(p);
		return p;
	}

	@Override
	public void deleteProduit(Produit p) {
		Session s=sf.getCurrentSession();
		// Ecrire la requete
		String req="DELETE FROM Produit AS p WHERE p.idProduit=:pIdProduit";
		
		// Query 
		Query query=s.createQuery(req);
		
		// Parametres
		query.setParameter("pIdProduit", p.getIdProduit());
		
		// Envoyer la requete
		query.executeUpdate();
		
		
		
	}

	@Override
	public Produit updateProduit(Produit p) {
		Session s=sf.getCurrentSession();
		// Ecrire la requete
		Produit pOut=(Produit) s.get(Produit.class, p.getIdProduit());
		pOut.setDesignation(p.getDesignation());
		pOut.setDescription(p.getDescription());
		pOut.setPrix(p.getPrix());
		pOut.setQuantite(p.getQuantite());
		pOut.setPhoto(p.getPhoto());
		pOut.setCategorie(p.getCategorie());
		
		s.saveOrUpdate(pOut);
		
		return pOut;
	}
	
	

}
