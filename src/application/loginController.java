package application;

import connectivity.ConnectionClass;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField loginUsername;

	@FXML
	private PasswordField loginPassword;

	@FXML
	private Button loginButton;

	@FXML
	private Label incorrectlabel;

	@FXML
	private Button signUpButton;

	@FXML
	void initialize() {
		signUpButton.setOnAction(event -> {
			signUpButton.getScene().getWindow().hide();
			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(getClass().getResource("signUp.fxml"));
			try {
				fxmlLoader.setRoot(fxmlLoader.getRoot());
				fxmlLoader.load();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Parent root = fxmlLoader.getRoot();
			Stage stage = new Stage();
			stage.setScene(new Scene(root));
			stage.showAndWait();
		});
		loginButton.setOnAction(event -> {
			checkLogin();
		});
	}

	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	private void checkLogin() {
		ConnectionClass cc = new ConnectionClass();
		Connection connection = cc.getConnection();
		String username = loginUsername.getText().toString();
		String password = loginPassword.getText().toString();

		String sql = "select * from emp where username = ? AND password = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();

			if (!resultSet.next()) {
				incorrectlabel.setText("Incorrect Username or Password");
			} else {
				showMainScreen();
			}

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}

	/*
	 * CODE FOR ICORRECT CREDIT ENTERED
	 * incorrectLabel.setText("Incorrect Username or Password");
	 */
	private void showMainScreen() {
		loginButton.getScene().getWindow().hide();
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(getClass().getResource("mainPage.fxml"));
		try {
			fxmlLoader.setRoot(fxmlLoader.getRoot());
			fxmlLoader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Parent root = fxmlLoader.getRoot();
		Stage stage = new Stage();
		stage.setScene(new Scene(root));
		stage.showAndWait();
	}

}
