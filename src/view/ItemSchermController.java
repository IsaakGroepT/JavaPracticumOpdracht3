package view;

import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import model.ItemTypes;

public class ItemSchermController {
	@FXML
	private ChoiceBox<ItemTypes> itemKeuze;
	@FXML
	private Button btnAnnuleren;

	@FXML
	private void initialize()
	{
		itemKeuze.setItems(FXCollections.observableArrayList(Arrays.asList(ItemTypes.values())));
		
		btnAnnuleren.setOnAction(e -> {
			Stage stage = (Stage) btnAnnuleren.getScene().getWindow();
			stage.close();
		});
	}
}
