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
 * @author Isaak Malik
 */
public class DatumVerschil {

	private int dagen;
	private int maanden;
	
    /**
     * Constructor:
     * Bereken het verschil tussen twee data. Er wordt rekening gehouden met schrikkeljaren
     * en het exact aantal dagen voor de maanden.
     * 
     * @param datum
     * @return
     * @throws Exception 
     */
	public DatumVerschil(Datum datum1, Datum datum2) throws Exception
    {
    	if (datum1.equals(datum2))
    	{
    		return;
    	}
    	
    	// De recentste datum moet afgetrokken worden door de oudste
    	Datum recentsteDatum = datum1.kleinerDan(datum2) ? datum1 : datum2;
    	Datum oudsteDatum = datum1.kleinerDan(datum2) ? datum2 : datum1;
    	//Datum oudsteDatumOrigineel = oudsteDatum;
    	//System.out.println(recentsteDatum);
    	//System.out.println(oudsteDatum);
    	
    	// Indien niet in dezelfde maand of hetzelfde jaar, plus 1 maand doen
    	while (datum1.getMaand() != datum2.getMaand() || datum1.getJaar() != datum2.getJaar())
    	{
    		int aantalDagenVoorMaand = Maanden.get(oudsteDatum.getMaand())
    									.aantalDagen(oudsteDatum.getJaar());
    		// Voeg een maand toe
    		oudsteDatum.veranderDatum(aantalDagenVoorMaand);
    		// Onthoud de dagen
    		dagen += aantalDagenVoorMaand;
    		// En nu maanden onthouden
    		maanden++;
    	}
    	// Vermeerder met resterende dagen van het verschil in datum
    	dagen += recentsteDatum.getDag() - oudsteDatum.getDag();
    	// Verminder met een maand als dag van recentsteDatum > oudsteDatum
    	maanden -= (recentsteDatum.getDag() < oudsteDatum.getDag() ? 1 : 0);
    }
	
	/**
	 * 
	 * @return
	 */
	public int getDagen()
	{
		return dagen;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getMaanden()
	{
		return maanden;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getJaren()
	{
		return maanden / 12;
	}
}
