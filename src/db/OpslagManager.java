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

package db;

import db.bestand.BestandOpslagStrategy;
import db.derby.DerbyOpslagStrategy;
import model.OpslagStrategieen;

/**
 * De client voor de opslag strategie
 * 
 * @author Isaak Malik
 */
public class OpslagManager {
	private static OpslagStrategy strategy;
	
	/**
	 * Singleton constructor
	 */
	private OpslagManager(){}

	/**
	 * Eerst moet de strategy type worden ingesteld
	 * 
	 * @param strategieType 
	 */
	public static void setStrategy(OpslagStrategieen strategieType)
	{
		switch (strategieType) {
			case DATABANK:
				strategy = new DerbyOpslagStrategy();
				break;
			case BESTAND:
				strategy = new BestandOpslagStrategy();
				break;
			case EXCEL:
				// Nog niks
				break;
		}
	}
	
	/**
	 * Zo halen we het object op
	 * 
	 * @return 
	 */
	public static OpslagStrategy getInstance()
	{
		return strategy;
	}
}
