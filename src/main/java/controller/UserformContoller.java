package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import dto.Tm.UserTm;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class UserformContoller {

    public void initialize(){
    }
    public AnchorPane pane;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtPassword;

    public void submitButtonOnAction(ActionEvent event) {
        UserTm c = new UserTm(txtEmail.getText(),
                     txtPassword.getText()
        );
        String sql = "INSERT INTO employee VALUES('\" + c.getEmail() + \"', '\" + c.getPassword() + \"')";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/company", "root", "shivika123");
            Statement stm = connection.createStatement();
            int result = stm.executeUpdate(sql);

            String correctPassword = null;

            if (txtPassword != null && txtPassword.getText().equals(correctPassword)) {
                new Alert(Alert.AlertType.INFORMATION, "Login successfull!").show();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Password is Incorrect!").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }


    public void passwordButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("View/Resetform.fxml"))));
            stage.setTitle("RESET PASSWORD");
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backButtonOnAction(ActionEvent event) {
        Stage stage = (Stage) pane.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("View/Dashboardform.fxml"))));
            stage.show();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("An error occurred while navigating back.");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
            e.printStackTrace();
        }
    }
}
