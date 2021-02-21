package edu.uw.rgm.account;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.account.AccountFactory;
import edu.uw.ext.framework.dao.AccountDao;

/**
 * A simple account manager that has no persistence, and accepts every login.
 * @author Odinahon Saydahmedova
 *
 */
public final class SimpleAccountManager implements edu.uw.ext.framework.account.AccountManager{

	private static final Logger log = LoggerFactory.getLogger(SimpleAccountManager.class);
	private static final String ENCODING = "ISO-8859-1";
	private static final String ALGORITHM = "SHA-256";
	private AccountDao dao;
	private AccountFactory accountFactory;
	
	/**
	 * Creates a new Simple account manager using the specified AccountDao for persistence.
	 * @param dao - the DAO to use for persistence
	 */
	public SimpleAccountManager(AccountDao dao) {
		this.dao = dao;
		try(ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("context.xml")){
			accountFactory = appContext.getBean(AccountFactory.class);
		}catch (final BeansException ex) {
			log.error("Unable to create account manager.", ex);
		}
	}

	/**
	 * Used to persist an account.
	 * @param account - the account to persist
	 * @throws edu.uw.ext.framework.account.AccountException - if operation fails
	 */
	@Override
	public void persist(Account account) throws AccountException {
		dao.setAccount(account);
	}

	/**
	 * Lookup an account based on accountName.
	 *	@param accountName - the name of the desired account
	 *@return the account if located otherwise null
	 *@throws edu.uw.ext.framework.account.AccountException - if operation fails
	 */
	@Override
	public Account getAccount(String accountName) throws AccountException {
		final Account acct = dao.getAccount(accountName);
		if(acct != null) {
			acct.registerAccountManager(this);
		}
		return acct;
	}

	/**
	 *	Remove the account.
	 *@param accountName - the name of the account to remove
	 *@throws edu.uw.ext.framework.account.AccountException - if operation fails
	 */
	@Override
	public synchronized void deleteAccount(final String accountName) throws AccountException {
		final Account acct = dao.getAccount(accountName);
		if(acct != null) {
			dao.deleteAccount(accountName);
		}
	}

	/**
	 * Creates an account.
	 * @param accountName - the name for account to add
	 * @param password - the password used to gain access to the account
	 * @param balance - the initial balance of the account
	 * @return the newly created account
	 * @throws edu.uw.ext.framework.account.AccountException - if the account already exists, or account creation fails for any reason
	 */
	@Override
	public Account createAccount(final String accountName, 
								 final String password, 
								 final int balance) 
	throws AccountException {
		if(dao.getAccount(accountName) == null) {
			final byte [] passwordHash = hashPassword(password);
			final Account acct = accountFactory.newAccount(accountName, passwordHash, balance);
			acct.registerAccountManager(this);
			persist(acct);
			return acct;
		}else {
			throw new AccountException("Account name already in use");
		}
	}
	private byte [] hashPassword(final String password) throws AccountException{
		try {
			final MessageDigest md = MessageDigest.getInstance(ALGORITHM);
			md.update(password.getBytes(ENCODING));
			return md.digest();
		}catch(final NoSuchAlgorithmException e) {
			throw new AccountException("Unable to find hash algorithm",e);
		}catch(final UnsupportedEncodingException e) {
			throw new AccountException(String.format("Unable to find character encoding: %s", ENCODING));
		}
	}
	/**
	 * Check whether a login is valid. AccountName must exist and password must match.
	 *	@param accountName - name of account the password is to be validated for
	 *@param password - password is to be validated
	 *@return true if password is valid for account identified by username
	 *@throws edu.uw.ext.framework.account.AccountException - if error occurs accessing accounts
	 */
	@Override
	public synchronized boolean validateLogin(final String accountName, 
											  final String password) 
			throws AccountException {
		boolean valid = false;
		final Account account = getAccount(accountName);
		if(account != null) {
			final byte [] passwordHash = hashPassword(password);
			valid = MessageDigest.isEqual(account.getPasswordHash(), passwordHash);
		}
		return valid;
	}

	/**
	 * Closes the account manager.
	 * @throws edu.uw.ext.framework.account.AccountException - if the DAO can't be closed
	 */
	@Override
	public void close() throws AccountException {
		dao.close();
		dao = null;
	}

}
