package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class enterRollNumberController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField labelRollNumber;

	@FXML
	private Button showResultButton;

	@FXML
	private ImageView goBack;

	@FXML
	void initialize() {
		showResultButton.setOnAction(event -> {

			showResultButton.getScene().getWindow().hide();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("displayRecord.fxml"));
			try {
				loader.setRoot(loader.getRoot());
				loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Parent root = loader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		});
		goBack.setOnMouseClicked(event -> {
			goBack.getScene().getWindow().hide();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("mainPage.fxml"));
			try {
				loader.setRoot(loader.getRoot());
				loader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Parent root = loader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.show();
		});
	}
}
