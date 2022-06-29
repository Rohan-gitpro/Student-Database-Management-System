package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import connectivity.ConnectionClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class registerStudentController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView goBack;

	@FXML
	private TextField rollNumber;

	@FXML
	private TextField name;

	@FXML
	private Button registerButton;

	@FXML
	private TextField email;

	@FXML
	private TextField locationfield;

	@FXML
	private TextField number;

	@FXML
	private TextField bloddgroup;

	@FXML
	private CheckBox maleCheckBox;

	@FXML
	private CheckBox femaleCheckBox;

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
		registerButton.setOnAction(event -> {
			registerStudent();
			registerButton.getScene().getWindow().hide();
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

	PreparedStatement preparedStatement = null;

	private void registerStudent() {
		ConnectionClass cc = new ConnectionClass();
		Connection connection = cc.getConnection();
		int RollNumber = Integer.parseInt(rollNumber.getText());
		String Name = name.getText().trim();
		String gender;
		if (femaleCheckBox.isSelected()) {
			gender = "Female";
		} else {
			gender = "Male";
		}
		String Email = email.getText().trim();
		String Location = locationfield.getText().trim();
		String Number = number.getText();
		String BloodGroup = bloddgroup.getText().trim();
		String insert = "INSERT INTO sdms.student(roll,name,gender,email,location,number,bloodgroup) VALUES(?,?,?,?,?,?,?);";

		String insert1 = "INSERT INTO sdms.stumarks(roll) VALUES(?)";
		try {
			preparedStatement = connection.prepareStatement(insert);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			preparedStatement.setInt(1, RollNumber);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			preparedStatement.setString(2, Name);
			preparedStatement.setString(3, gender);
			preparedStatement.setString(4, Email);
			preparedStatement.setString(5, Location);
			preparedStatement.setString(6, Number);
			preparedStatement.setString(7, BloodGroup);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			preparedStatement = connection.prepareStatement(insert1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			preparedStatement.setInt(1, RollNumber);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
