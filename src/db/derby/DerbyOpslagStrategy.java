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

package db.derby;

import db.OpslagStrategy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Isaak Malik
 */
public class DerbyOpslagStrategy implements OpslagStrategy {
	
	private static String dbLink = "jdbc:derby:db/MediaWinkelDB;user=test;password=test";
	private static Connection db;
	
	public DerbyOpslagStrategy()
	{
		try {
			db = DriverManager.getConnection(dbLink);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void lezen()
	{
		
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void opslaan()
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	private void itemsLezen()
	{
		
	}
	
	private void klantenLezen()
	{
		
	}
		
	private void uitleningenLezen()
	{
		
	}
	
	private void itemsSchrijven()
	{
		
	}
	
	private void klantenSchrijven()
	{
		
	}
	
	private void uitleningenSchrijven()
	{
		
	}
}
