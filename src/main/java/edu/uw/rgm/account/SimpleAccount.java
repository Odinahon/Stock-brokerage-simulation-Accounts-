package edu.uw.rgm.account;



import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.account.AccountManager;
import edu.uw.ext.framework.account.Address;
import edu.uw.ext.framework.account.CreditCard;
import edu.uw.ext.framework.order.Order;

/**
 * Implementation of the Account interface as a JavaBean.
 * @author Odinahon Saydahmedova
 *
 */
public final class SimpleAccount implements edu.uw.ext.framework.account.Account{
	/**
	 * 
	 */
	private static final long serialVersionUID = -71229613021782210L;
	private static final Logger logger = LoggerFactory.getLogger(SimpleAccount.class);
	private static final int MIN_ACC_LEN=8;
	private static final int MIN_BALANCE=100_000;
	private int balance=Integer.MIN_VALUE;
	private String acctName;
	private byte[] passwordHash;
	private String fullName;
	private Address address;
	private String phone;
	private String email;
	private CreditCard creditCard;
	private transient Optional<AccountManager> acctMngr = Optional.empty();
	/**
	 * No parameter constructor, required by JavaBeans.
	 */
	public SimpleAccount(){
		
	}
	
	
	/**
	 * Constructor, validates length of account name and the initial balance.
	 * @param acctName - the account name
	 * @param passwordHash - the password hash
	 * @param balance - the balance
	 * @throws edu.uw.ext.framework.account.AccountException - if the account name is too short or balance too low
	 */
	public SimpleAccount( final String acctName, final byte[] passwordHash, final int balance) throws edu.uw.ext.framework.account.AccountException{
		if(balance<MIN_BALANCE) {
			final String msg = String.format("Account creation failed for, account '%s' minimum and %d balance", acctName, balance);
			logger.warn(msg);
			throw new edu.uw.ext.framework.account.AccountException(msg);
		}
		else {
			setName(acctName);
			setPasswordHash(passwordHash);
			this.balance = balance;
		}
		
	}

	/**
	 *	Get the account name.
	 *	@return the name of the account
	 */
	@Override
	public String getName() {
		return acctName;
	}

	/**
	 *	Sets the account name. 
	 *	The name will be checked for minimum length MIN_ACCT_LEN.. 
	 *	This operation is not generally used but is provided for JavaBean conformance.
	 *	@param acctName - the value to be set for the account name
	 *	@throws AccountException- if the account name is to short
	 */
	@Override
	public void setName(String acctName) throws AccountException {
		if(acctName == null || acctName.length() < MIN_ACC_LEN) {
			final String msg = String.format("Account name '%s' is unacceptable.", acctName);
			logger.warn(msg);
			throw new AccountException(msg);
		}else {
			this.acctName=acctName;
			acctMngr.ifPresent((m) -> persistConsumer(m));
		}
	}

	/**
	 *	Gets the hashed password.
	 * 	@return the hashed password.
	 */
	@Override
	public byte[] getPasswordHash() {
		byte [] copy = null;
		if(passwordHash != null) {
			copy = new byte[passwordHash.length];
			System.arraycopy(passwordHash, 0, copy, 0, passwordHash.length);
		}
		return copy;
	}

	/**
	 *	Sets the hashed password.
	 *	@param passwordHash - the value to be set for the password hash
	 */
	@Override
	public void setPasswordHash(byte[] passwordHash) {
		byte [] copy = null;
		if(passwordHash != null) {
			copy = new byte[passwordHash.length];
			System.arraycopy(passwordHash, 0, copy, 0, passwordHash.length);
		}
		this.passwordHash=copy;
		acctMngr.ifPresent((m) -> persistConsumer(m));
	}

	/**
	 *	Gets the account balance.
	 *@return the current balance of the account.
	 */
	@Override
	public int getBalance() {
		return balance;
	}

	/**
	 *	Sets the account balance.
	 * @param balance - the value to set the balance to
	 */
	@Override
	public void setBalance(int balance) {
		this.balance=balance;
		acctMngr.ifPresent((m) -> persistConsumer(m));
	}

	/**
	 * Gets the full name of the account holder.
	 * @return the account holders full name.
	 */
	@Override
	public String getFullName() {
		return fullName;
	}

	/**
	 * Sets the full name of the account holder.
	 * @param fullName - the account holders full name.
	 */
	@Override
	public void setFullName(String fullName) {
		this.fullName=fullName;
		acctMngr.ifPresent((m) -> persistConsumer(m));
	}

	/**
	 *	Gets the account address.
	 *@return the account address.
	 */
	@Override
	public Address getAddress() {
		return address;
	}

	/**
	 * Sets the account address.
	 * @param address - the address for the account
	 *
	 */
	@Override
	public void setAddress(Address address) {
		this.address=address;
		acctMngr.ifPresent((m) -> persistConsumer(m));
	}

	/**
	 * Gets the phone number.
	 * @return the phone number.
	 */
	@Override
	public String getPhone() {
		return phone;
	}

	/**
	 *	Sets the account phone number.
	 *@param phone - value for the account phone number
	 */
	@Override
	public void setPhone(String phone) {
		this.phone=phone;
		acctMngr.ifPresent((m) -> persistConsumer(m));
	}

	/**
	 *Gets the email address.
	 *@return the email address.
	 */
	@Override
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets the account email address.
	 * @param email - the email address
	 */
	@Override
	public void setEmail(String email) {
		this.email=email;
		acctMngr.ifPresent((m) -> persistConsumer(m));
	}

	/**
	 * Gets the account credit card.
	 * @return the credit card
	 */
	@Override
	public CreditCard getCreditCard() {
		return creditCard;
	}

	/**
	 *Sets the account credit card.
	 *@param card - the value to be set for the credit card
	 */
	@Override
	public void setCreditCard(CreditCard card) {
		this.creditCard=card;
		acctMngr.ifPresent((m) -> persistConsumer(m));
	}

	/**
	 *Sets the account manager responsible for persisting/managing this account. 
	 *This may be invoked exactly once on any given account, any subsequent invocations should be ignored.
	 *@param accountManager - the account manager
	 */
	@Override
	public void registerAccountManager(AccountManager accountManager) {
		if(!acctMngr.isPresent()) {
			acctMngr = Optional.of(accountManager);
			acctMngr.ifPresent((m)->persistConsumer(m));
		}else {
			logger.info("Attempting to set the account manager, after it has been initialized.");
		}
	}
	private void persistConsumer(final AccountManager accountManager) {
		try {
			accountManager.persist(this);
		}catch(AccountException ex) {
			logger.error(String.format("Failed to persist account %s.", acctName),ex);
		}
	}

	/**
	 * Incorporates the effect of an order in the balance. 
	 * Increments or decrements the account balance by the execution price * number of shares in the order and then persists the account, using the account manager.
	 * @param order - the order to be reflected in the account

	 * @param executionPrice - the price the order was executed at
	 */
	@Override
	public void reflectOrder(Order order, int executionPrice) {
		balance += order.valueOfOrder(executionPrice);
		acctMngr.ifPresent((m) -> persistConsumer(m));
	}

}
