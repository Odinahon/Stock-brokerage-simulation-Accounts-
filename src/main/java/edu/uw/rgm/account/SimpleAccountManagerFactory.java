package edu.uw.rgm.account;

import edu.uw.ext.framework.account.AccountManager;
import edu.uw.ext.framework.dao.AccountDao;

/**
 * A simple implementation of the AccountManagerFactory that instantiates the SimpleAccountManager.
 * @author Odinahon Saydahmedova
 *
 */
public final class SimpleAccountManagerFactory implements edu.uw.ext.framework.account.AccountManagerFactory{

//	/**
//	 * no parameter constructor
//	 */
//	public SimpleAccountManagerFactory() {
//		
//	}
	/**
	 * Instantiates a new SimpleAccountManager instance.
	 * @param dao - the data access object to be used by the account manager
	 * @return a newly instantiated SimpleAccountManager
	 */
	@Override
	public AccountManager newAccountManager(AccountDao dao) {
		return new SimpleAccountManager(dao);
	}

}
