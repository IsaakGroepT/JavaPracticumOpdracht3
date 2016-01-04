package view;

import db.ItemLijst;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Item;
import model.ItemTypes;

public class HoofdSchermController {
	@FXML
	private Button btnToevoegen;
	@FXML
	private Button btnVerwijderen;
	@FXML
	private TableView<Item> tblItems;
	@FXML
	private TableColumn<Item, String> ID;
	@FXML
	private TableColumn<Item, ItemTypes> Type;
	@FXML
	private TableColumn<Item, String> Titel;
	@FXML
	private TableColumn<Item, String> Uitgeleend;
	
	@FXML
	private void initialize()
	{
		/*
		 * Button acties
		 */
		
		btnToevoegen.setOnAction(e -> {
			toonItemScherm();
		});
		
		btnVerwijderen.setOnAction(e -> {
			System.out.println("Button verwijderen (item) geclickt!");
		});
		
		/**
		 * Item tabel
		 */
		
		ObservableList<Item> items = FXCollections.observableArrayList(ItemLijst.getItems());
		
		ID.setCellValueFactory(new PropertyValueFactory<Item, String>("ID"));
		Type.setCellValueFactory(new PropertyValueFactory<Item, ItemTypes>("Type"));
		Titel.setCellValueFactory(new PropertyValueFactory<Item, String>("Titel"));
		Uitgeleend.setCellValueFactory(new PropertyValueFactory<Item, String>("UitgeleendString"));
		
		tblItems.setItems(items);
		
		/*
		 * 
		 */
	}
	
	private void toonItemScherm()
	{
		Parent itemScherm = null;
		
		try {
			itemScherm = FXMLLoader.load(getClass().getResource("ItemScherm.fxml"));
		} catch (Exception e) {
			System.out.println("Kon itemScherm niet laden: " + e.getMessage());
		}

		Stage stage = new Stage();
		stage.setTitle("Voeg item toe");
		stage.setResizable(false);
		stage.setAlwaysOnTop(true);
		stage.setScene(new Scene(itemScherm));  
		stage.show();
	}
}
