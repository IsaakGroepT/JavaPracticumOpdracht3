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
