package edu.uw.rgm.account;

/**
 * A straight forward implementation of the Address interface as a JavaBean.
 * @author Odinahon Saydahmedova
 *
 */
public final class SimpleAddress implements edu.uw.ext.framework.account.Address{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7519106486150971696L;
	/**
	 * 
	 */
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
	/**
	 * no parameter constructor.
	 */
	public SimpleAddress() {
		super();
	}
	/**
	 * Gets the street address.
	 * @return the street address.
	 */
	@Override
	public String getStreetAddress() {
		return streetAddress;
	}

	/**
	 * Sets the street address.
	 * @param streetAddress - the street address
	 */
	@Override
	public void setStreetAddress(String streetAddress) {
		this.streetAddress=streetAddress;
		
	}

	/**
	 * Gets the city.
	 * @return the city
	 */
	@Override
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 * @param city - the city.
	 */
	@Override
	public void setCity(String city) {
		this.city=city;
	}

	/**
	 * Gets the state.
	 * @return the state.
	 */
	@Override
	public String getState() {
		return state;
	}

	/**
	 * Sets the state.
	 * @param state - the state.
	 */
	@Override
	public void setState(String state) {
		this.state=state;
	}

	/**
	 * Gets the ZIP code.
	 * @return the ZIP code
	 */
	@Override
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * Sets the ZIP code.
	 *@param zip - the ZIP code
	 */
	@Override
	public void setZipCode(String zip) {
		this.zipCode=zip;
	}
	/**
	 * Concatenates the street, city, state and zip properties into the standard one line postal format.
	 * @return the formated address string
	 */
	@Override
	public String toString() {
		return String.format("%s, %s, %s, %s", streetAddress, city, state, zipCode);
	}
	

}
