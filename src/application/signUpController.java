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
import javafx.stage.Stage;

public class signUpController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField userName;

	@FXML
	private TextField password;

	@FXML
	private TextField email;

	@FXML
	private CheckBox maleCheckbox;

	@FXML
	private CheckBox femaleCheckBox;

	@FXML
	private Button signUpButton;

	@FXML
	void initialize() {
		signUpButton.setOnAction(event -> {
			try {
				createUser();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			signUpButton.getScene().getWindow().hide();
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("login.fxml"));

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

	private void createUser() throws SQLException {
		ConnectionClass cc = new ConnectionClass();
		Connection connection = cc.getConnection();
		String UserName = userName.getText().trim();
		String Password = password.getText().trim();
		String gender;
		String Email = email.getText().trim();
		if (femaleCheckBox.isSelected()) {
			gender = "Female";
		} else {
			gender = "Male";
		}
//		INSERT INTO `sdms`.`emp`
//		(
//		`username`,
//		`password`,
//		`email`,
//		`gender`)
//		VALUES
//		("vinayak","pass","vinayaksingh11111@gmail.com","Male");
//		String insert="INSERT INTO sdms.emp (username, password, email, gender) VALUES(?,?,?,?)";
		String insert = "INSERT INTO `sdms`.`emp`\r\n" + "		(\r\n" + "		`username`,\r\n"
				+ "		`password`,\r\n" + "		`email`,\r\n" + "		`gender`)\r\n" + "		VALUES\r\n"
				+ "		(?,?,?,?);";
		preparedStatement = connection.prepareStatement(insert);
		preparedStatement.setString(1, UserName);
		preparedStatement.setString(2, Password);
		preparedStatement.setString(3, Email);
		preparedStatement.setString(4, gender);
		preparedStatement.executeUpdate();
	}
}
