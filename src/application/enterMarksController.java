package application;

import connectivity.ConnectionClass;
import java.sql.*;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class enterMarksController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField apMarks;

	@FXML
	private TextField m3Marks;

	@FXML
	private TextField coaMarks;

	@FXML
	private TextField idbmsMarks;

	@FXML
	private TextField otaMarks;

	@FXML
	private TextField efeMarks;

	@FXML
	private Button submitMarksButton;

	@FXML
	private TextField rollNumber;

	@FXML
	private ImageView goBack;

	@FXML
	void initialize() {
		submitMarksButton.setOnAction(event -> {

			storeMarksInDB();
			submitMarksButton.getScene().getWindow().hide();
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

	private void storeMarksInDB() {
		ConnectionClass cc2 = new ConnectionClass();
		Connection connection = cc2.getConnection();
		String rollNum = rollNumber.getText().toString();
		int mark1 = Integer.parseInt(apMarks.getText());
		int mark2 = Integer.parseInt(m3Marks.getText());
		int mark3 = Integer.parseInt(coaMarks.getText());
		int mark4 = Integer.parseInt(idbmsMarks.getText());
		int mark5 = Integer.parseInt(efeMarks.getText());
		int mark6 = Integer.parseInt(otaMarks.getText());
		DecimalFormat df = new DecimalFormat("#.##");
		double gpa;
		if ((mark1 + mark2 + mark3 + mark4 + mark5 + mark6) / 6 == 100) {
			gpa = 10.0;
		} else {
			gpa = Double.parseDouble(df.format((((mark1 + mark2 + mark3 + mark4 + mark5 + mark6) / 6) / 9.5)));
		}

		String sql = "update stumarks set APmarks = ? , MMmarks = ? , COAmarks = ? , DBMSmarks = ?,EFEmarks = ? , OTAmarks = ? , cgpa = ? where roll = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, mark1);
			preparedStatement.setInt(2, mark2);
			preparedStatement.setInt(3, mark3);
			preparedStatement.setInt(4, mark4);
			preparedStatement.setInt(5, mark5);
			preparedStatement.setInt(6, mark6);
			preparedStatement.setDouble(7, gpa);
			preparedStatement.setString(8, rollNum);
			preparedStatement.executeUpdate();

		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}

	}
}
