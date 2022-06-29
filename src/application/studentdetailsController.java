package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import connectivity.ConnectionClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class studentdetailsController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView goBack;

	@FXML
	private Button showDetailsButton;

	@FXML
	private TextField rollNumber;

	@FXML
	private Label name;

	@FXML
	private Label gender;

	@FXML
	private Label email;

	@FXML
	private Label locationLabel;

	@FXML
	private Label number;

	@FXML
	private Label bloodgroup;

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
		showDetailsButton.setOnAction(event -> {
			showDetails();
		});
	}

	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	private void showDetails() {
		ConnectionClass cc2 = new ConnectionClass();
		Connection connection = cc2.getConnection();
		String r = rollNumber.getText().toString();
		String sql = "select * from student where roll = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, r);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			String n = resultSet.getString("name").toUpperCase();
			name.setText(n);
			gender.setText(resultSet.getString("gender"));
			email.setText(resultSet.getString("email"));
			locationLabel.setText(resultSet.getString("location"));
			number.setText(resultSet.getString("number"));
			bloodgroup.setText(resultSet.getString("bloodgroup"));

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}
}
