package view;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage scherm) throws IOException
	{
		Parent root;
		root = FXMLLoader.load(getClass().getResource("HoofdScherm.fxml"));
		Scene scene = new Scene(root, 698, 400);
		
		// Sluit alle schermen als hoofdscherm wordt gesloten
		scherm.setOnCloseRequest(e -> {
			Platform.exit();
		});
		
		scherm.setTitle("Beheer CD/DVD/Games Winkel");
		scherm.setResizable(false);
		scherm.setScene(scene);
		scherm.show();
	}
}
