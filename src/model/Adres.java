package model;

/**
 * @Autor: x
 * @Purpose: Adres object, aangepast aan Belgische toestanden
 */
public class Adres {
	private String straat;
	private int nummer;
	private String box;
	private int postcode;
	private String stad;
	private String phone;
	private String mobilePhone;
	private String email;
	private String fax;

	/**
	 * @return the street
	 */
	public String getStreet() {
		return straat;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(String street) {
		this.straat = street;
	}

	/**
	 * @return the number
	 */
	public int getNumber() {
		return nummer;
	}

	/**
	 * @param number
	 *            the number to set
	 */
	public void setNumber(int number) {
		this.nummer = number;
	}

	/**
	 * @return the box
	 */
	public String getBox() {
		return box;
	}

	/**
	 * @param box
	 *            the box to set
	 */
	public void setBox(String box) {
		this.box = box;
	}

	/**
	 * @return the zipCode
	 */
	public int getPostcode() {
		return postcode;
	}

	/**
	 * @param zipCode
	 *            the zipCode to set
	 */
	public void setPostcode(int zipCode) {
		this.postcode = zipCode;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return stad;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(String city) {
		this.stad = city;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone
	 *            the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the mobilePhone
	 */
	public String getMobilePhone() {
		return mobilePhone;
	}

	/**
	 * @param mobilePhone
	 *            the mobilePhone to set
	 */
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax
	 *            the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	public String toString() {
		return String.format("%s %s bus %s\n%s %s\n%s\nTel.: %s\nGsm.: %s\nFax: %s", getStreet(), getNumber(), getBox(),
				getPostcode(), getCity(), getEmail(), getPhone(),
				getMobilePhone(), getFax());
	}
}
