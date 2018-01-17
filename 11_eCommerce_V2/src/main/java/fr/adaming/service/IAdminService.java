package fr.adaming.service;

import javax.ejb.Local;

import fr.adaming.dao.Admin;

@Local
public interface IAdminService {

	public Admin isExist(Admin admin) throws Exception;
	
}
