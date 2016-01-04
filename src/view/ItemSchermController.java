package view;

import java.lang.reflect.Constructor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Item;
import model.ItemTypes;

public class ItemSchermController {
	@FXML
	private ChoiceBox<ItemTypes> cbItemKeuze;
	@FXML
	private Button btnAnnuleren;
	@FXML
	private Button btnToevoegen;
	@FXML
	private TextField tfNaam;
	@FXML
	private TextField tfBeschrijving;

	@FXML
	private void initialize()
	{
		// Item types
		cbItemKeuze.setItems(FXCollections.observableArrayList(ItemTypes.values()));
		
		btnAnnuleren.setOnAction(e -> {
			sluitScherm();
		});
		
		btnToevoegen.setOnAction(e -> {
			try {
				itemToevoegen();
				sluitScherm();
			} catch (Exception e1) {
				System.out.println("Probleem: " + e1.getMessage());
			}
		});
	}
	
	private void itemToevoegen() throws Exception
	{
		// Validatie
		if (cbItemKeuze.getValue() == null ) {
			throw new Exception("Je moet een type kiezen");
		} else if (tfNaam.getText().trim().isEmpty()) {
			throw new Exception("Je moet een titel ingeven");
		}
		ObservableList<Item> items = FXCollections.observableArrayList();
		
		Class<?> klasse = Class.forName("model." + cbItemKeuze.getValue().toString());
		Constructor<?> cons = klasse.getConstructor(String.class);
		cons.newInstance(tfNaam.getText());
	}
	
	private void sluitScherm()
	{
		Stage stage = (Stage) btnAnnuleren.getScene().getWindow();
		stage.close();
	}
}
