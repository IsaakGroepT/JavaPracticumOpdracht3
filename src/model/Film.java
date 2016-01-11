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
public class Film extends Item {
	
	/*
	 * Dus komt neer op: 5 euro voor drie dagen, voor elke dag daarna 2 euro per dag
	 */
	private final double prijs = 5;
	private final int dagenBasisprijsGeldig = 3;
	private final double prijsPerDagNaInbegrepenDagen = 2;
	
	public Film(String titel)
	{
		super(titel);
	}
	
	public Film(String titel, UUID id)
	{
		super(titel, id);
	}
	
	/**
	 * Sommige items hebben een minimumperiode voor de prijs die ze betalen
	 * 
	 * @return
	 */
//	public int getMinimumLeenPeriode()
//	{
//		return dagenBasisprijsGeldig;
//	}
	
	/**
	 * Geeft gewoon de prijs terug. In dit geval (item is een film) wordt de prijs per dag
	 * na 3 dagen gebruikt. Standaard zitten altijd 3 dagen inbegrepen voor een prijs van 5 euro.
	 * Dus om de boete te berekenen moet eerst bekeken worden of het leentermijn > 3
	 */
	@Override
	public double getPrijs()
	{
		return prijsPerDagNaInbegrepenDagen;
	}
	
	/**
	 * Geeft de prijs voor het item terug berekend voor het aantal dagen. 
	 * 
	 * @param aantalDagen
	 * @return
	 */
	@Override
	public double getPrijs(int aantalDagen)
	{
		double prijs = this.prijs;
		
		int dagenOver = aantalDagen - dagenBasisprijsGeldig;
		
		if (dagenOver > 0) {
			prijs += dagenOver * prijsPerDagNaInbegrepenDagen;
		}
		
		return prijs;
	}
	
	/**
	 * Toevoeging van de prijs aan de originele toString() methode
	 */
	@Override
	public String toString()
	{
		return super.toString() + " - " + prijs;
	}
}
