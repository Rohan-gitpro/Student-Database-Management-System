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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class displayRecordController {
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private Label studentName;

	@FXML
	private ImageView goBack;

	@FXML
	private TextField studentRollNumber;

	@FXML
	private Label studentAPMarks;

	@FXML
	private Label studentMiiiMarks;

	@FXML
	private Label studentCOAMarks;

	@FXML
	private Label studentIDBMSMarks;

	@FXML
	private Label studentOTAMarks;

	@FXML
	private Label studentEFEMarks;

	@FXML
	private TextField rollNumber;

	@FXML
	private Label studentCGPA;

	@FXML
	private Button showResultButton;

	@FXML
	void initialize() {

		showResultButton.setOnAction(event -> {

			showResult();

			// CODE FOR ENTERING THE MARKS INTO THE DATABASE SHOULD BE HERE
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

	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;

	private void showResult() {
		ConnectionClass cc2 = new ConnectionClass();
		Connection connection = cc2.getConnection();
		String r = studentRollNumber.getText().toString();
		String sql = "select * from stumarks where roll = ?";
		String sql1="select (name) from sdms.student where roll=?";
		String n = null;
		try {
			preparedStatement = connection.prepareStatement(sql1);
			preparedStatement.setString(1, r);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			n = resultSet.getString("name").toUpperCase();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, r);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			studentName.setText(n);
			studentAPMarks.setText(Integer.toString(resultSet.getInt("APmarks")));
			studentCOAMarks.setText(Integer.toString(resultSet.getInt("COAmarks")));
			studentEFEMarks.setText(Integer.toString(resultSet.getInt("EFEmarks")));
			studentCGPA.setText(Double.toString(resultSet.getDouble("cgpa")));
			studentMiiiMarks.setText(Integer.toString(resultSet.getInt("MMmarks")));
			studentIDBMSMarks.setText(Integer.toString(resultSet.getInt("DBMSmarks")));
			studentOTAMarks.setText(Integer.toString(resultSet.getInt("OTAmarks")));

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}
}
