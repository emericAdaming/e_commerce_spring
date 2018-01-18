package fr.adaming.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itextpdf.text.log.SysoCounter;

import fr.adaming.modele.Admin;

@Repository
public class AdminDaoImpl implements IAdminDao {

	@Autowired //injection du collaborateur sf
	private SessionFactory sf;
		
	public void setSf(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Admin isExist(Admin admin) throws Exception {
		System.out.println("Recup Admin");
		Session s=sf.getCurrentSession();

		// Construire directement la requete JPQL
		String req = "SELECT a FROM Admin AS a WHERE a.mail=:pMail AND a.mdp=:pMdp";

		// Création d'un Query
		Query query = s.createQuery(req);

		// Passage des paramètres
		query.setParameter("pMail", admin.getMail());
		query.setParameter("pMdp", admin.getMdp());

		// Envoyer la requête et récupérer l'agent
		Admin adminOut = (Admin) query.uniqueResult();
		System.out.println("Admin:"+adminOut);

		return adminOut;

	}

}
