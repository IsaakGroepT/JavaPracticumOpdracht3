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

package controller;

import db.ItemLijst;
import db.KlantenRegister;
import db.UitleningenRegister;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.Item;
import model.Klant;
import model.ObservableSubject;
import model.Observer;
import model.Uitlening;

/**
 * @author Isaak Malik
 */
public class SchermKlantenZoneController implements Observer {
	@FXML
	private ComboBox<Integer> cbKlantIDs;
	@FXML
	private TextArea taLijstUitleningen;
	@FXML
	private Label lbNaam;

	@FXML
	private void initialize()
	{
		// Registreer observer
		ObservableSubject uitleningListener = new SchermController();
		uitleningListener.registreerObserver(this);
		
		cbKlantIDs.setItems(FXCollections.observableArrayList(KlantenRegister.getKlantIDs()));
		
		cbKlantIDs.setOnAction(e -> {
			ComboBox cbIDs = (ComboBox) e.getSource();
			Klant klant = KlantenRegister.getKlantObvID((int) cbIDs.getValue());
			List<Uitlening> uitleningen = UitleningenRegister.getUitleningenVoorKlant(klant.getID());
			
			lbNaam.setText(klant.getVoornaam() + " " + klant.getAchternaam());
			
			taLijstUitleningen.clear();
			
			for (int n = 0; n < uitleningen.size(); n++ ) {
				Item item = ItemLijst.getItemObvID(uitleningen.get(n).getItemID());
				
				taLijstUitleningen.appendText(
					"Titel: " + item.getTitel() + " - Dagen: "
						+ uitleningen.get(n).getAantalDagen() + " - Prijs: " 
						+ uitleningen.get(n).getPrijs() + "\n"
				);
			}
			
			// Observer deregistreren
			Stage stage = (Stage) taLijstUitleningen.getScene().getWindow();
			stage.setOnCloseRequest(ev -> {
				uitleningListener.verwijderObserver(this);
			});
		});
	}

	/**
	 * Onze Observer actie:
	 * 
	 * Enkel de geselecteerde klant moet geupdatete worden, want alle uitleningen worden 
	 * sowieso getoont na selectie
	 * 
	 * @param klantID 
	 */
	@Override
	public void update()
	{
		int klantID = cbKlantIDs.getValue();
		List<Uitlening> uitleningen = UitleningenRegister.getUitleningenVoorKlant(klantID);
		
		taLijstUitleningen.clear();
			
		for (int n = 0; n < uitleningen.size(); n++ ) {
			Item item = ItemLijst.getItemObvID(uitleningen.get(n).getItemID());

			taLijstUitleningen.appendText(
				"Titel: " + item.getTitel() + " - Dagen: "
					+ uitleningen.get(n).getAantalDagen() + " - Prijs: " 
					+ uitleningen.get(n).getPrijs() + "\n"
			);
		}
	}
}
