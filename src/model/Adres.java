/*
 * The MIT License
 *
 * Copyright 2016 Isaak Malik.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
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
	private String gemeente;
	private String email;

	/**
	 * @return the street
	 */
	public String getStraat()
	{
		return straat;
	}

	/**
	 * @param street
	 *           the street to set
	 */
	public void setStraat(String street)
	{
		this.straat = street;
	}

	/**
	 * @return the number
	 */
	public int getNummer()
	{
		return nummer;
	}

	/**
	 * @param number
	 *           the number to set
	 */
	public void setNummer(int number)
	{
		this.nummer = number;
	}

	/**
	 * @return the box
	 */
	public String getBox()
	{
		return box;
	}

	/**
	 * @param box
	 *           the box to set
	 */
	public void setBox(String box)
	{
		this.box = box;
	}

	/**
	 * @return the zipCode
	 */
	public int getPostcode()
	{
		return postcode;
	}

	/**
	 * @param zipCode
	 *           the zipCode to set
	 */
	public void setPostcode(int zipCode)
	{
		this.postcode = zipCode;
	}

	/**
	 * @return the city
	 */
	public String getGemeente()
	{
		return gemeente;
	}

	/**
	 * @param city
	 *           the city to set
	 */
	public void setGemeente(String city)
	{
		this.gemeente = city;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @param email
	 *           the email to set
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	public String toString()
	{
		return String.format("%s %s bus %s\n%s %s\n%s\n", getStraat(), getNummer(), getBox(), getPostcode(),
				getGemeente(), getEmail());
	}
}
