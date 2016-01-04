package controller;

import java.lang.reflect.Constructor;
import db.ItemLijst;
import db.Klant;
import db.KlantenRegister;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Adres;
import model.Item;
import model.ItemTypes;

public class SchermController {
	@FXML
	private TableView<Item> tblItems;
	@FXML
	private TableView<Klant> tblKlanten;
//	@FXML
//	private TableView<Uitlening> tblUitleningen;
	@FXML
	private Button btnItemToevoegen;
	@FXML
	private Button btnKlantToevoegen;
	@FXML
	private Button btnRegistreer;
	@FXML
	private ChoiceBox<ItemTypes> cbTypes;
	@FXML
	private ChoiceBox<Item> cbItems;
	@FXML
	private ChoiceBox<Klant> cbKlanten;
	@FXML
	private TextField tfTitel;
	@FXML
	private TextField tfVoornaam;
	@FXML
	private TextField tfAchternaam;
	@FXML
	private TextField tfStraat;
	@FXML
	private TextField tfNummer;
	@FXML
	private TextField tfPostcode;
	@FXML
	private TextField tfGemeente;
	@FXML
	private TextField tfEmail;
	@FXML
	private TableColumn<Item, String> tcID;
	@FXML
	private TableColumn<Item, ItemTypes> tcType;
	@FXML
	private TableColumn<Item, String> tcTitel;
	@FXML
	private TableColumn<Item, String> tcUitgeleend;
	@FXML
	private TableColumn<Klant, Integer> tcKlantID;
	@FXML
	private TableColumn<Klant, String> tcVoornaam;
	@FXML
	private TableColumn<Klant, String> tcAchternaam;
	@FXML
	private TableColumn<Klant, String> tcStraat;
	@FXML
	private TableColumn<Klant, Integer> tcNummer;
	@FXML
	private TableColumn<Klant, Integer> tcPostcode;
	@FXML
	private TableColumn<Klant, String> tcGemeente;
	@FXML
	private TableColumn<Klant, String> tcEmail;
	
	@FXML
	private void initialize()
	{
		/*
		 * Items tabblad
		 */
		tcID.setCellValueFactory(new PropertyValueFactory<Item, String>("ID"));
		tcType.setCellValueFactory(new PropertyValueFactory<Item, ItemTypes>("Type"));
		tcTitel.setCellValueFactory(new PropertyValueFactory<Item, String>("Titel"));
		tcUitgeleend.setCellValueFactory(new PropertyValueFactory<Item, String>("UitgeleendString"));
		tblItems.setItems(ItemLijst.getItems());
		
		// Menu om rijen te verwijderen
		MenuItem menuVerwijder = new MenuItem("Verwijder Item");
		menuVerwijder.setOnAction(e -> {
			Item item = ItemLijst.getItems().get(tblItems.getSelectionModel().getSelectedIndex());

			if (item != null) { 
				ItemLijst.getItems().remove(item);
			}
		});
		tblItems.setContextMenu(new ContextMenu(menuVerwijder));
		
		// Item types
		cbTypes.setItems(FXCollections.observableArrayList(ItemTypes.values()));
		
		btnItemToevoegen.setOnAction(e -> {
			try {
				itemToevoegen();
			} catch (Exception e1) {
				System.out.println("Probleem bij het toevoegen van de item: " + e1.getMessage());
			}
		});
		
		/*
		 * Klanten tabblad
		 */
		tcKlantID.setCellValueFactory(new PropertyValueFactory<Klant, Integer>("ID"));
		tcVoornaam.setCellValueFactory(new PropertyValueFactory<Klant, String>("voornaam"));
		tcAchternaam.setCellValueFactory(new PropertyValueFactory<Klant, String>("achternaam"));
		// Op deze manier halen we de waarde het het Adres object in de Klant object. Tricky business!
		tcStraat.setCellValueFactory(cell -> {
			return new ReadOnlyObjectWrapper<String>(cell.getValue().getAdres().getStraat());
		});
		tcNummer.setCellValueFactory(cell -> {
			return new ReadOnlyObjectWrapper<Integer>(cell.getValue().getAdres().getNummer());
		});
		tcPostcode.setCellValueFactory(cell -> {
			return new ReadOnlyObjectWrapper<Integer>(cell.getValue().getAdres().getPostcode());
		});
		tcGemeente.setCellValueFactory(cell -> {
			return new ReadOnlyObjectWrapper<String>(cell.getValue().getAdres().getGemeente());
		});
		tcEmail.setCellValueFactory(cell -> {
			return new ReadOnlyObjectWrapper<String>(cell.getValue().getAdres().getEmail());
		});
		
		
		tblKlanten.setItems(KlantenRegister.getKlanten());
		
		btnKlantToevoegen.setOnAction(e -> {
			try {
				klantToevoegen();
			} catch (Exception e1) {
				System.out.println("Probleem bij het toevoegen van de klant: " + e1.getMessage());
			}
		});
	}
	
	private void itemToevoegen() throws Exception
	{
		// Validatie
		if (cbTypes.getValue() == null ) {
			throw new Exception("Je moet een type kiezen");
		} else if (tfTitel.getText().trim().isEmpty()) {
			throw new Exception("Je moet een titel ingeven");
		}
		
		// Gelukt!
		
		// Maak een object van de String (CD, Film of Spel)
		Class<?> klasse = Class.forName("model." + cbTypes.getValue().toString());
		// en geef de parameters door aan de constructor
		Constructor<?> cons = klasse.getConstructor(String.class);
		cons.newInstance(tfTitel.getText());
	}
	
	private void klantToevoegen() throws Exception
	{
		// Validatie
		if (tfVoornaam.getText().trim().isEmpty() ) {
			throw new Exception("Geef de voornaam van de klant in");
		} else if (tfAchternaam.getText().trim().isEmpty() ) {
			throw new Exception("Geef de achternaam van de klant in");
		} else if (tfStraat.getText().trim().isEmpty() ) {
			throw new Exception("Geef de straat van de klant in");
		} else if (tfNummer.getText().trim().isEmpty() ) {
			throw new Exception("Geef het huisnummer van de klant in");
		} else if (tfPostcode.getText().trim().isEmpty() ) {
			throw new Exception("Geef de postcode van de klant in");
		} else if (tfGemeente.getText().trim().isEmpty() ) {
			throw new Exception("Geef de gemeente van de klant in");
		} else if (tfEmail.getText().trim().isEmpty() ) {
			throw new Exception("Geef het emailadres van de klant in");
		}
		// Gelukt!
		
		Adres adres = new Adres();
		adres.setStraat(tfStraat.getText());
		adres.setNummer(Integer.parseInt(tfNummer.getText()));
		adres.setPostcode(Integer.parseInt(tfPostcode.getText()));
		adres.setGemeente(tfGemeente.getText());
		adres.setEmail(tfEmail.getText());
		
		KlantenRegister.klantToevoegen(new Klant(tfVoornaam.getText(), tfAchternaam.getText(), adres));
	}
}
