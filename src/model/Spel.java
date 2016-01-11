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

import java.util.UUID;

/**
 * @author Isaak Malik
 */
public class Spel extends Item {
	
	private final double prijs = 3;
	
	public Spel(String titel)
	{
		super(titel);
	}
	
	public Spel(String titel, UUID id)
	{
		super(titel, id);
	}
	
	/**
	 * Geeft gewoon de prijs terug
	 */
	@Override
	public double getPrijs()
	{
		return prijs;
	}
	
	/**
	 * Geeft de prijs voor het item terug. 
	 * 
	 * @param aantalDagen
	 * @return
	 */
	@Override
	public double getPrijs(int aantalDagen)
	{
		return prijs * aantalDagen;
	}
	
	/**
	 * Toevoeging van de prijs aan de originele toString() methode
	 */
	@Override
	public String toString()
	{
		return super.toString() + " - " + prijs;
	}

	/*@Override
	public int getMinimumLeenPeriode()
	{
		return 1;
	}*/
}
