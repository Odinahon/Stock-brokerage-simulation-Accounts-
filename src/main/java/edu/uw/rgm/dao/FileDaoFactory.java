package edu.uw.rgm.dao;

import edu.uw.ext.framework.dao.AccountDao;
import edu.uw.ext.framework.dao.DaoFactoryException;

/**
 * Implementation of DaoFactory that creates a FileAccountDao instance.
 * @author Odinahon Saydahmedova
 *
 */
public final class FileDaoFactory implements edu.uw.ext.framework.dao.DaoFactory{

	public FileDaoFactory() {
		
	}
	/**
	 * Instantiates an instance of FileAccountDao.
	 *@return a new instance of FileAccountDao
	 *@throws edu.uw.ext.framework.dao.DaoFactoryException - if instantiation fails
	 */
	@Override
	public AccountDao getAccountDao() throws DaoFactoryException {
		return new FileAccountDao();
	}

}
