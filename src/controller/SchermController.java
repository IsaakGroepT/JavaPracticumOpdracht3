package controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import com.sun.prism.paint.Color;

import db.ItemLijst;
import db.Klant;
import db.KlantenRegister;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import model.Adres;
import model.Item;
import model.ItemTypes;
import model.Exceptions.ItemInputProbleemException;
import model.Exceptions.KlantInputProbleemException;
import view.Main;

public class SchermController {
	@FXML
	private TableView<Item> tblItems;
	@FXML
	private TableView<Klant> tblKlanten;
//	@FXML
//	private TableView<Uitlening> tblUitleningen;
	@FXML
	private Button btnItemToevoegen, btnKlantToevoegen, btnRegistreer, btnItemsZoeken;
	@FXML
	private ComboBox<ItemTypes> cbTypes;
	@FXML
	private ChoiceBox<Item> cbItems;
	@FXML
	private ChoiceBox<Klant> cbKlanten;
	@FXML
	private TextField tfTitel, tfVoornaam, tfAchternaam, tfStraat, tfNummer, tfPostcode,
	tfGemeente, TextField, tfEmail;
	@FXML
	private TableColumn<Item, ItemTypes> tcType;
	@FXML
	private TableColumn<Item, String> tcID, tcTitel, tcUitgeleend;
	@FXML
	private TableColumn<Klant, String> tcVoornaam, tcAchternaam, tcStraat, tcGemeente, tcEmail;
	@FXML
	private TableColumn<Klant, Integer> tcKlantID, tcNummer, tcPostcode;
	
	@FXML
	private void initialize()
	{
		/****************
		 * Items tabblad
		 ****************/
		tcID.setCellValueFactory(new PropertyValueFactory<Item, String>("ID"));
		tcType.setCellValueFactory(new PropertyValueFactory<Item, ItemTypes>("Type"));
		tcTitel.setCellValueFactory(new PropertyValueFactory<Item, String>("Titel"));
		tcUitgeleend.setCellValueFactory(new PropertyValueFactory<Item, String>("UitgeleendString"));
		tblItems.setItems(ItemLijst.getItems());
		
		// Menu om rijen te verwijderen voor de Item tabel
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
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Informatie Dialoog");
			alert.setHeaderText(null);
			
			try {
				itemToevoegen();
			} catch (ItemInputProbleemException b) {
				// Onze eigen Exception laat Dialog schermpjes zien
				Main.toonFoutbericht(b.getMessage());
			} catch (Exception e1) {
				// Dialog met meer details
				Main.toonMegaFoutbericht(e1);
			}
		});
		
		// Functionaliteit voor de knop "Zoeken"
		btnItemsZoeken.setOnAction(ae -> {
			Main.toonSchermZoeken();
		});
		btnItemsZoeken.setStyle("-fx-font-weight: bold");
		
		/******************
		 * Klanten tabblad
		 ******************/
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
		
		// Menu om rijen te verwijderen uit de Klant tabel
		MenuItem menuKlantVerwijder = new MenuItem("Verwijder Klant");
		menuKlantVerwijder.setOnAction(e -> {
			Klant klant = KlantenRegister.getKlanten().get(tblKlanten.getSelectionModel().getSelectedIndex());

			if (klant != null) { 
				KlantenRegister.getKlanten().remove(klant);
			}
		});
		tblKlanten.setContextMenu(new ContextMenu(menuKlantVerwijder));
		
		btnKlantToevoegen.setOnAction(e -> {
			try {
				klantToevoegen();
			} catch (KlantInputProbleemException b) {
				// Onze eigen Exception laat Dialog schermpjes zien
				Main.toonFoutbericht(b.getMessage());
			} catch (Exception e1) {
				// Dialog met meer details
				Main.toonMegaFoutbericht(e1);
			}
		});
	}
	
	private void itemToevoegen() throws ClassNotFoundException, NoSuchMethodException, 
	SecurityException, InstantiationException, IllegalAccessException,
	IllegalArgumentException, InvocationTargetException, ItemInputProbleemException
	{
		// Validatie	
		if (cbTypes.getValue() == null ) {
			throw new ItemInputProbleemException("Je moet het type van de item selecteren");
		} else if (tfTitel.getText().trim().isEmpty()) {
			throw new ItemInputProbleemException("Je moet de titel van de item invoeren");
		}
		
		// Gelukt!
		
		// Maak een object van de String (CD, Film of Spel)
		Class<?> klasse = Class.forName("model." + cbTypes.getValue().toString());
		// en geef de parameters door aan de constructor
		Constructor<?> cons = klasse.getConstructor(String.class);
		cons.newInstance(tfTitel.getText());
	}
	
	private void klantToevoegen() throws KlantInputProbleemException
	{
		// Validatie
		//TODO: dialogs
		if (tfVoornaam.getText().trim().isEmpty() ) {
			throw new KlantInputProbleemException("Geef de voornaam van de klant in");
		} else if (tfAchternaam.getText().trim().isEmpty() ) {
			throw new KlantInputProbleemException("Geef de achternaam van de klant in");
		} else if (tfStraat.getText().trim().isEmpty() ) {
			throw new KlantInputProbleemException("Geef de straat van de klant in");
		} else if (tfNummer.getText().trim().isEmpty() ) {
			throw new KlantInputProbleemException("Geef het huisnummer van de klant in");
		} else if (tfPostcode.getText().trim().isEmpty() ) {
			throw new KlantInputProbleemException("Geef de postcode van de klant in");
		} else if (tfGemeente.getText().trim().isEmpty() ) {
			throw new KlantInputProbleemException("Geef de gemeente van de klant in");
		} else if (tfEmail.getText().trim().isEmpty() ) {
			throw new KlantInputProbleemException("Geef het emailadres van de klant in");
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
