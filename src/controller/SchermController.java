package controller;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import db.ItemLijst;
import db.KlantenRegister;
import db.UitleningenRegister;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Adres;
import model.Datum;
import model.Item;
import model.ItemTypes;
import model.Klant;
import model.Uitlening;
import model.Exceptions.ItemInputProbleemException;
import model.Exceptions.KlantInputProbleemException;
import model.Exceptions.UitleningInputProbleemException;
import view.Main;

public class SchermController {
	@FXML
	private TableView<Item> tblItems;
	@FXML
	private TableView<Klant> tblKlanten;
	@FXML
	private TableView<Uitlening> tblUitleningen;
	@FXML
	private Button btnItemToevoegen, btnKlantToevoegen, btnRegistreer, btnItemsZoeken;
	@FXML
	private ComboBox<ItemTypes> cbTypes;
	@FXML
	private ComboBox<Integer> cbKlantIDs;
	@FXML
	private ComboBox<UUID> cbItemIDs;
	@FXML
	private TextField tfTitel, tfVoornaam, tfAchternaam, tfStraat, tfNummer, tfPostcode,
	tfGemeente, TextField, tfEmail, tfAantalDagen;
	@FXML
	private TableColumn<Item, ItemTypes> tcType;
	@FXML
	private TableColumn<Item, String> tcID, tcTitel, tcUitgeleend;
	@FXML
	private TableColumn<Klant, String> tcVoornaam, tcAchternaam, tcStraat, tcGemeente, tcEmail;
	@FXML
	private TableColumn<Klant, Integer> tcKlantID, tcNummer, tcPostcode;
	@FXML
	private TableColumn<Uitlening, Integer> tcUitleningKlantID;
	@FXML
	private TableColumn<Uitlening, UUID> tcUitleningItemID;
	@FXML
	private TableColumn<Uitlening, Datum> tcUitleenStart;
	@FXML
	private TableColumn<Uitlening, Datum> tcUitleenEinde;
	
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
		
		/*********************
		 * Uitleningen tabblad
		 *********************/
		tcUitleningKlantID.setCellValueFactory(new PropertyValueFactory<Uitlening, Integer>("klantID"));
		tcUitleningItemID.setCellValueFactory(new PropertyValueFactory<Uitlening, UUID>("itemID"));
		tcUitleenStart.setCellValueFactory(new PropertyValueFactory<Uitlening, Datum>("startDatum"));
		tcUitleenEinde.setCellValueFactory(new PropertyValueFactory<Uitlening, Datum>("eindDatum"));
		
		tblUitleningen.setItems(UitleningenRegister.getUitleningen());
		
		// Menu om rijen te verwijderen uit de Uitlening tabel
		MenuItem menuUitleningVerwijder = new MenuItem("Stop Uitlening");
		menuUitleningVerwijder.setOnAction(e -> {
			Uitlening uitlening = UitleningenRegister.getUitleningen().get(tblUitleningen.getSelectionModel().getSelectedIndex());

			if (uitlening != null) {
				try {
					int dagenTeLaat = uitlening.getAantalDagenTeLaat();
					
					// Niet echt efficiënt, maar de opdracht was om de uitlening te kunnen stoppen
					// door ingave van Item ID. De uitlening wordt ook door deze methode verwijderd uit
					// de lijst
					double boete = UitleningenRegister.uitleningStoppen(uitlening.getItemID());
					
					if (uitlening.isTeLaat()) {
						toonInformatieScherm("Er moet een boete betaald worden", 
								"Het item is " + dagenTeLaat + " dagen te laat. Boete: " + boete);
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		tblUitleningen.setContextMenu(new ContextMenu(menuUitleningVerwijder));
		
		cbItemIDs.setItems(FXCollections.observableArrayList(ItemLijst.getUitleendbareItemIDs()));
		cbKlantIDs.setItems(FXCollections.observableArrayList(KlantenRegister.getKlantIDs()));
		
		btnRegistreer.setOnAction(e -> {
			try {
				uitleningToevoegen();
			} catch (UitleningInputProbleemException b) {
				// Onze eigen Exception laat Dialog schermpjes zien
				Main.toonFoutbericht(b.getMessage());
			} catch (Exception e1) {
				// Dialog met meer details
				Main.toonMegaFoutbericht(e1);
			}
		});
		
	}
	
	/**
	 * Itemke toevoegen woehoe
	 * 
	 * @throws ClassNotFoundException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 * @throws ItemInputProbleemException
	 */
	private void itemToevoegen() throws ClassNotFoundException, NoSuchMethodException, 
	SecurityException, InstantiationException, IllegalAccessException,
	IllegalArgumentException, InvocationTargetException, ItemInputProbleemException
	{
		// Validatie...
		if (cbTypes.getValue() == null) {
			throw new ItemInputProbleemException("Je moet het type van de item selecteren");
		} else if (tfTitel.getText().trim().isEmpty()) {
			throw new ItemInputProbleemException("Je moet de titel van de item invoeren");
		}
		
		//...gelukt!
		
		// Maak een object van de String (CD, Film of Spel)
		Class<?> klasse = Class.forName("model." + cbTypes.getValue().toString());
		// en geef de parameters door aan de constructor
		Constructor<?> cons = klasse.getConstructor(String.class);
		cons.newInstance(tfTitel.getText());
	}
	
	/*
	 * Een geldkoe toevoegen!
	 */
	private void klantToevoegen() throws KlantInputProbleemException
	{
		// Validatie...
		if (tfVoornaam.getText().trim().isEmpty()) {
			throw new KlantInputProbleemException("Geef de voornaam van de klant in");
		} else if (tfAchternaam.getText().trim().isEmpty()) {
			throw new KlantInputProbleemException("Geef de achternaam van de klant in");
		} else if (tfStraat.getText().trim().isEmpty()) {
			throw new KlantInputProbleemException("Geef de straat van de klant in");
		} else if (tfNummer.getText().trim().isEmpty()) {
			throw new KlantInputProbleemException("Geef het huisnummer van de klant in");
		} else if (tfPostcode.getText().trim().isEmpty()) {
			throw new KlantInputProbleemException("Geef de postcode van de klant in");
		} else if (tfGemeente.getText().trim().isEmpty()) {
			throw new KlantInputProbleemException("Geef de gemeente van de klant in");
		} else if (tfEmail.getText().trim().isEmpty()) {
			throw new KlantInputProbleemException("Geef het emailadres van de klant in");
		}
		//...gelukt!
		
		Adres adres = new Adres();
		adres.setStraat(tfStraat.getText());
		adres.setNummer(Integer.parseInt(tfNummer.getText()));
		adres.setPostcode(Integer.parseInt(tfPostcode.getText()));
		adres.setGemeente(tfGemeente.getText());
		adres.setEmail(tfEmail.getText());
		
		new Klant(tfVoornaam.getText(), tfAchternaam.getText(), adres);
	}
	
	/**
	 * Nieuwe uitlening = ping ping!
	 *
	 * Probleem: ik kan de Item ID niet verwijderen uit de combobox 
	 * wanneer de uitlening is aangemaakt. Een herstart is dan vereist...
	 * 
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	private void uitleningToevoegen() throws NumberFormatException, Exception
	{
		// Validatie...
		if (cbItemIDs.getValue() == null) {
			throw new UitleningInputProbleemException("Selecteer de Item ID van de uitlening");
		} else if (cbKlantIDs.getValue() == null) {
			throw new UitleningInputProbleemException("Selecteer de Klant ID van de uitlening");
		} else if (tfAantalDagen.getText().trim().isEmpty() ) {
			throw new UitleningInputProbleemException("Geef het aantal dagen van de uitlening in");
		} else if (Integer.parseInt(tfAantalDagen.getText()) > 0 && Integer.parseInt(tfAantalDagen.getText()) >= 31) {
			throw new UitleningInputProbleemException("Het item kan maar tussen 1 en 31 dagen uitgeleend worden");
		}
		//...gelukt!
		
		double prijs = new Uitlening(cbKlantIDs.getValue(), cbItemIDs.getValue(), Integer.parseInt(tfAantalDagen.getText())).getPrijs();
		
		toonInformatieScherm("Prijs Van De Uitlening", "Deze uitlening kost: " + Double.toString(prijs) + " EUR");
	}
	
	/**
	 * 
	 * @param titel
	 * @param text
	 */
	private void toonInformatieScherm(String titel, String text)
	{
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titel);
		alert.setHeaderText(null);
		alert.setContentText(text);
		alert.show();
	}
}
