package edu.uw.rgm.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;

/**
 * An implementation of AccountFactory that creates SimpleAccount instances.
 * @author Odinahon Saydahmedova
 *
 */
public final class SimpleAccountFactory implements edu.uw.ext.framework.account.AccountFactory{

	private static final Logger logger = LoggerFactory.getLogger(SimpleAccountFactory.class);
	/**
	 * constructor without parameters.
	 */
	public SimpleAccountFactory() {
		
	}
	/**
	 *Instantiations a an instance of SimpleAccount.
	 *@param accountName - the account name
	 *@param hashedPassword - the password hash
	 *@param initialBalance - the balance
	 *@return the newly instantiated account, or null if unable to instantiate the account
	 */
	@Override
	public Account newAccount(final String accountName, 
							  final byte[] hashedPassword, 
							  final int initialBalance) {
		
		Account acct = null;
		try {
			acct = new SimpleAccount(accountName, hashedPassword, initialBalance);
			if(logger.isInfoEnabled()) {
				logger.info("Created account: '%s', balance = %d",
						accountName, initialBalance);
			}
		}catch(final AccountException ex) {
			final String msg = String.format("Account creation failed for , account '%s', balance = %d", accountName, initialBalance);
			logger.warn(msg);
		}
		return acct;
	}
}
