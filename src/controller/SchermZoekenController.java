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
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Item;
import model.ItemTypes;
import model.Exceptions.ItemInputProbleemException;
import view.Main;

/**
 * @author Isaak Malik
 */
public class SchermZoekenController {
	@FXML
	private ComboBox<ItemTypes> cbZoekType;
	@FXML
	private TextField tfZoekItem;
	@FXML
	private TextArea taResultaat;
	@FXML
	private Button btnZoek, btnToonGeformatteerd;
	
	
	@FXML
	private void initialize()
	{
		taResultaat.setStyle("-fx-font-family: Courier New; -fx-font-size: 13px");
		cbZoekType.setItems(FXCollections.observableArrayList(ItemTypes.values()));
		
		btnZoek.setOnAction(ev -> {
			try {
				voorbeeldZoekenOpKernwoord(tfZoekItem.getText(), cbZoekType.getValue());
			} catch (ItemInputProbleemException e) {
				Stage stage = (Stage) cbZoekType.getScene().getWindow();
				stage.setAlwaysOnTop(false);
				Main.toonFoutbericht(e.getMessage());
			}
		});
		
		btnToonGeformatteerd.setOnAction(e -> {
			taResultaat.clear();
			taResultaat.appendText(ItemLijst.getItemsGeformateerd());
		});
	}
	
	/**
	 * Zoeken op basis van een gegeven (deel van een) woord.
	 * @throws ItemInputProbleemException 
	 */
	private void voorbeeldZoekenOpKernwoord(String text, ItemTypes type) throws ItemInputProbleemException
	{
		text = text.trim();
		
		if (text.isEmpty()) {
			throw new ItemInputProbleemException("Je hebt geen zoekterm ingegeven");
		} else if (type == null) {
			throw new ItemInputProbleemException("Je moet een item type selecteren");
		}
		taResultaat.clear();
		
		for (Item item : ItemLijst.zoekItemsOpDeelString(text.trim(), type)) {
			taResultaat.appendText(item.toString() + System.lineSeparator());
		}
	}
}
