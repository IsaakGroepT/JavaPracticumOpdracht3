package view;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage scherm) throws IOException
	{
		Parent root;
		root = FXMLLoader.load(getClass().getResource("Scherm.fxml"));
		Scene scene = new Scene(root);
		
		// Sluit alle schermen als hoofdscherm wordt gesloten
		scherm.setOnCloseRequest(e -> {
			Platform.exit();
		});
		
		scherm.setTitle("Media Winkel");
		scherm.setResizable(false);
		scherm.setScene(scene);
		scherm.show();
	}
	
	public static void toonSchermZoeken()
	{
		Parent itemScherm = null;
		
		try {
			itemScherm = FXMLLoader.load(Main.class.getResource("SchermZoeken.fxml"));
		} catch (Exception e) {
			System.out.println("Kon SchermZoeken niet laden: " + e.getMessage());
		}

		Stage stage = new Stage();
		stage.setTitle("Zoeken naar items");
		stage.setResizable(false);
		stage.setAlwaysOnTop(true);
		stage.setScene(new Scene(itemScherm));  
		stage.show();
	}
	
	/**
	 * Voor inputfouter bij het aanmaken van items enz.
	 * @param bericht
	 */
	public static void toonFoutbericht(String bericht)
	{
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning Dialog");
		alert.setHeaderText(null);
		alert.setContentText(bericht);
		alert.showAndWait();
	}
	
	/**
	 * Voor echte Exceptions
	 * 
	 * @param e
	 */
	public static void toonMegaFoutbericht(Exception e)
	{
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Exception Dialog");
		alert.setHeaderText("Er is een MEGA Exception voorgevallen!");
		alert.setContentText(e.getMessage());

		// Laat ons toe "Verberg Details" te implementeren
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		String exceptionText = sw.toString();

		Label label = new Label("De exception stacktrace was:");

		TextArea textArea = new TextArea(exceptionText);
		textArea.setEditable(false);
		textArea.setWrapText(true);

		textArea.setMaxWidth(Double.MAX_VALUE);
		textArea.setMaxHeight(Double.MAX_VALUE);
		GridPane.setVgrow(textArea, Priority.ALWAYS);
		GridPane.setHgrow(textArea, Priority.ALWAYS);

		GridPane expContent = new GridPane();
		expContent.setMaxWidth(Double.MAX_VALUE);
		expContent.add(label, 0, 0);
		expContent.add(textArea, 0, 1);

		// Verberg Details toevoegen aan ons Dialog
		alert.getDialogPane().setExpandableContent(expContent);
		alert.showAndWait();
	}
}
