package edu.uw.rgm.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.account.Address;
import edu.uw.ext.framework.account.CreditCard;

/**
 * An AccountDao that persists the account information in a file.
 * @author Odinahon Saydahmedova
 *
 */
public final class FileAccountDao implements edu.uw.ext.framework.dao.AccountDao{
	private static final Logger logger = LoggerFactory.getLogger(FileAccountDao.class);
	private static final String ACCOUNT_FILENAME = "account.dat";
	private static final String ADDRESS_FILENAME = "address.properties";
	private static final String CREDITCARD_FILENAME = "creditcard.txt";
	private final File accountsDir = new File("target","accounts");
//	/**
//	 *  no parameter constructor.
//	 */
//	public FileAccountDao() {
//		
//	}
//	
	/**
	 * Lookup an account in the HashMap based on username.
	 * @param accountName - the name of the desired account 
	 * @return the account if located otherwise null
	 *
	 */
	@Override
	public Account getAccount(final String accountName) {
		Account account = null;
		final File accountDir = new File(accountsDir, accountName);
		if(accountDir.exists() && accountDir.isDirectory()) {
			try {
				File inFile = new File(accountDir, ACCOUNT_FILENAME);
				try(FileInputStream accntIn = new FileInputStream(inFile);){
					account = AccountSer.read(accntIn);
				}
				inFile = new File(accountDir, ADDRESS_FILENAME);
				if(inFile.exists()) {
					try(FileInputStream addrIn = new FileInputStream(inFile)){
						final Address address = AddressSer.read(addrIn);
						account.setAddress(address);
					}
				}
				inFile = new File(accountDir, CREDITCARD_FILENAME);
				if(inFile.exists()) {
					try(FileInputStream ccIn = new FileInputStream(inFile)){
						final CreditCard creditCard = CreditCardSer.read(ccIn);
						account.setCreditCard(creditCard);
					}
				}
			}catch (final IOException ex) {
				logger.warn(String.format("Unable to access or read account data, '%s'", account),ex);
			}catch(final AccountException ex) {
				logger.warn(String.format("Unable to process account files for account, '%s'", account),ex);
			}
		}
		return account;
	}

	/**
	 * Adds or updates an account.
	 * @param account - the account to add/update
	 * @throws edu.uw.ext.framework.account.AccountException - if operation fails
	 */
	@Override
	public void setAccount(Account account) throws AccountException {
		try {
			final File accountDir = new File(accountsDir, account.getName());
			final Address address = account.getAddress();
			final CreditCard card = account.getCreditCard();
			
			deleteFile(accountDir);
			if(!accountDir.exists()) {
				final boolean success = accountDir.mkdirs();
				if(!success) {
					throw new AccountException(
							String.format("Unable to create account directory, %s", accountDir));
				}
			}
			File outFile = new File(accountDir, ACCOUNT_FILENAME);
			try(FileOutputStream accntOut = new FileOutputStream(outFile)){
				AccountSer.write(accntOut, account);
			}
			if(address != null) {
				outFile = new File(accountDir, ADDRESS_FILENAME);
				try(FileOutputStream addrOut = new FileOutputStream(outFile)){
					AddressSer.write(addrOut, address);
				}
			}
			if(card != null) {
				outFile = new File(accountDir, CREDITCARD_FILENAME);
				try(FileOutputStream ccOut = new FileOutputStream(outFile)){
					CreditCardSer.write(ccOut, card);
				}
			}
		}catch (final IOException ex) {
			throw new AccountException("Unable to store account(s).",ex);
		}
	}

	/**
	 * Remove the account.
	 * @param accountName - the name of the account to remove
	 * @throws edu.uw.ext.framework.account.AccountException - if operation fails
	 */
	@Override
	public void deleteAccount(String accountName) throws AccountException {
		deleteFile(new File(accountsDir, accountName));
	}

	/**
	 * Remove all accounts. This is primarily available to facilitate testing.
	 * @throws edu.uw.ext.framework.account.AccountException - if operation fails
	 */
	@Override
	public void reset() throws AccountException {
		deleteFile(accountsDir);
	}
	
	private void deleteFile(final File file) {
		if(file.exists()) {
			if(file.isDirectory()) {
				final File[] files = file.listFiles();
				if(files !=null) {
					for(File currFile : files) {
						deleteFile(currFile);
					}
				}else{
					logger.warn(String.format("File inexplicably not a directory, %s", file.getAbsolutePath()));
				}
			}
			try {
				Files.delete(file.toPath());
			}catch(IOException e) {
				logger.warn(String.format("File deletion failed, %s", file.getAbsolutePath()),e);
			}
		}
	}

	/**
	 * Close the DAO.
	 *  @throws @throws edu.uw.ext.framework.account.AccountException - if operation fails
	 *
	 */
	@Override
	public void close() throws AccountException {
		// TODO Auto-generated method stub
		
	}

}
