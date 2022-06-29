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

public class displayScholarsController {

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ImageView goBack;

	@FXML
	private TextField rollNumberField;

	@FXML
	private Label studentRollNumber;

	@FXML
	private Label studentAPMarks;

	@FXML
	private Label studentCOAMarks;

	@FXML
	private Label studentEFEMarks;
	
	@FXML
	private Label studentMiiiMarks;
	
	@FXML
	private Label studentIDBMSMarks;

	@FXML
	private Label studentOTAMarks;

	@FXML
	private Label studentCGPA;

	@FXML
	private Button displayScholarsButton;

	@FXML
	void initialize() {
		

		displayScholarsButton.setOnAction(event -> {
			displayScholars();

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

		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;


		private void displayScholars() {

			ConnectionClass cc2 = new ConnectionClass();
			Connection connection = cc2.getConnection();
			
			String sql = "select * from stumarks order by cgpa desc limit 5;"
			try{
				preparedStatement = connection.prepareStatement(sql);
				
				resultSet = preparedStatement.executeQuery();
				while(resultSet.next())
				{
					studentRollNumber.setText(Integer.toString(resultSet.getString("roll")));
					studentAPMarks.setText(Integer.toString(resultSet.getInt("APmarks")));
					studentCOAMarks.setText(Integer.toString(resultSet.getInt("COAmarks")));
					studentEFEMarks.setText(Integer.toString(resultSet.getInt("EFEmarks")));
					studentCGPA.setText(Double.toString(resultSet.getDouble("cgpa")));
					studentMiiiMarks.setText(Integer.toString(resultSet.getInt("MMmarks")));
					studentIDBMSMarks.setText(Integer.toString(resultSet.getInt("DBMSmarks")));
					studentOTAMarks.setText(Integer.toString(resultSet.getInt("OTAmarks")));
				}
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}
	}

}
			
		
			
			
