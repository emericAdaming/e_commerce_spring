package fr.adaming.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.adaming.dao.IAdminDao;
import fr.adaming.modele.Admin;

@Service("aService") // Déclarer la classe comme un bean (Service)
@Transactional // Rendre toutes les méthodes transactionnables
public class AdminServiceImpl implements IAdminService{

	@Autowired
	private IAdminDao adminDao;
	
	
	
	// Setter pour l'injection dépendance (pas obligatoire ici)
	
	public void setAdminDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}


	@Override
	public Admin isExist(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		return adminDao.isExist(admin);
	}

}
