package fr.adaming.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import fr.adaming.dao.Admin;
import fr.adaming.dao.IAdminDao;

@Stateless
public class AdminServiceImpl implements IAdminService{

	@EJB
	public IAdminDao adminDao;
	
	// Getters & Setters
	
	public IAdminDao getAdminDao() {
		return adminDao;
	}



	public void setAdminDao(IAdminDao adminDao) {
		this.adminDao = adminDao;
	}


	@Override
	public Admin isExist(Admin admin) throws Exception {
		// TODO Auto-generated method stub
		return adminDao.isExist(admin);
	}

}
