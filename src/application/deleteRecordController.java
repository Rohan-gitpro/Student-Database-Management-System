package application;

import connectivity.ConnectionClass;
import java.sql.*;
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

public class deleteRecordController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView goBack;

	@FXML
	private TextField rollNumberField;

	@FXML
	private Button deleteRecordButton;

	@FXML
	void initialize() {
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

		deleteRecordButton.setOnAction(event -> {
			deleteRecord();
			deleteRecordButton.getScene().getWindow().hide();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("mainPage.fxml"));

			try {
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

	PreparedStatement preparedStatement = null;

	private void deleteRecord() {
		ConnectionClass cc2 = new ConnectionClass();
		Connection connection = cc2.getConnection();
		String r = rollNumberField.getText().toString();

		String sql = "delete from stumarks where roll = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, r);
			preparedStatement.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		String sql1 = "delete from student where roll = ?";
		try {
			preparedStatement = connection.prepareStatement(sql1);
			preparedStatement.setString(1, r);
			preparedStatement.executeUpdate();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}
}
