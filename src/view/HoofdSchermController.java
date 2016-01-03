package view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HoofdSchermController {
	@FXML
	private Button btnToevoegen;
	@FXML
	private Button btnVerwijderen;
	private Stage stage;
	
	@FXML
	private void initialize()
	{
		btnToevoegen.setOnAction(e -> {
			toonItemScherm();
		});
		
		btnVerwijderen.setOnAction(e -> {
			System.out.println("Button verwijderen (item) geclickt!");
		});
	}
	
	private void toonItemScherm()
	{
		Parent itemScherm = null;
		
		try {
			itemScherm = FXMLLoader.load(getClass().getResource("ItemScherm.fxml"));
		} catch (Exception e) {
			System.out.println("Kon itemScherm niet laden: " + e.getMessage());
		}
		
		stage = new Stage();
      stage.setTitle("Voeg item toe");
      stage.setResizable(false);
      stage.setAlwaysOnTop(true);
      stage.setScene(new Scene(itemScherm));  
      stage.show();
	}
}
