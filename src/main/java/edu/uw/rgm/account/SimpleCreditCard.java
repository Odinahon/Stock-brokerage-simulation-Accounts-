package edu.uw.rgm.account;

import edu.uw.ext.framework.account.CreditCard;

/**
 * A straight forward implementation of the CreditCard interface as a JavaBean.
 * @author Odinahon Saydahmedova
 *
 */
public final class SimpleCreditCard implements CreditCard{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5164821714743173311L;
	/**
	 * 
	 */
	
	private String issuer;
	private String type;
	private String holder;
	private String accountNumber;
	private String expirationDate;
	/**
	 * No parameter constructor, required by JavaBeans.
	 */
	public SimpleCreditCard() {
		super();
	}
	/**
	 * Gets the card issuer.
	 * @return the card issuer
	 */
	@Override
	public String getIssuer() {
		return issuer;
	}

	/**
	 * Sets the card issuer.
	 *@param issuer - the card issuer
	 */
	@Override
	public void setIssuer(String issuer) {
		this.issuer=issuer;
	}

	/**
	 * Gets the card type.
	 * @return the card type
	 */
	@Override
	public String getType() {
		return type;
	}

	/**
	 * Sets the card type.
	 * @param type- the card type
	 */
	@Override
	public void setType(String type) {
		this.type=type;
	}

	/**
	 * Gets the card holder's name.
	 * @return the card holder's name.
	 */
	@Override
	public String getHolder() {
		return holder;
	}

	/**
	 * Sets the card holder's name.
	 * @param name - the card holders name.
	 */
	@Override
	public void setHolder(String name) {
		this.holder=name;
	}

	/**
	 * Gets the card account number.
	 * @return the account number
	 */
	@Override
	public String getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Sets the card account number.
	 * @param accountNumber - the account number.
	 */
	@Override
	public void setAccountNumber(String accountNumber) {
		this.accountNumber= accountNumber;
	}

	/**
	 * Gets the card expiration date.
	 * @return the expiration date.
	 */
	@Override
	public String getExpirationDate() {
		return expirationDate;
	}

	/**
	 * Sets the card expiration date.
	 *@param expDate - the expiration date
	 */
	@Override
	public void setExpirationDate(String expDate) {
		this.expirationDate=expDate;
	}

}
