package fr.adaming.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import fr.adaming.modele.Admin;

public class AdminDaoImpl implements IAdminDao {

	@Autowired //injection du collaborateur sf
	private SessionFactory sf;
	
	
	// pas obligatoire
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Admin isExist(Admin admin) throws Exception {
		// Pas besoin de cr�er l'emf, l'em et d'ouvrir la tx

		// Construire directement la requete JPQL
		String req = "SELECT a FROM Admin AS a WHERE a.mail=:pMail AND a.mdp=:pMdp";

		// Cr�ation d'un Query
		Query query = s.createQuery(req);

		// Passage des param�tres
		query.setParameter("pMail", admin.getMail());
		query.setParameter("pMdp", admin.getMdp());

		// Envoyer la requ�te et r�cup�rer l'agent
		Admin adminOut = (Admin) query.getSingleResult();

		return adminOut;

	}

}
