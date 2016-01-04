package view;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
		//scherm.setResizable(false);
		scherm.setScene(scene);
		scherm.show();
	}
}
