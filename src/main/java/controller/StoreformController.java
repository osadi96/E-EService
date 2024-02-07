package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Objects;

public class StoreformController {
    public AnchorPane pane;

    public void AdminButtonOnAction(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Adminform.fxml")))));
            stage.setTitle("EMPLOYEE BOARD");
            Image image = new Image(getClass().getResourceAsStream("/Img/img1.png"));
            stage.getIcons().add(image);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void EmployeeButtonOnAction(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Employee.fxml")))));
            stage.setTitle("ADMIN BOARD");
            Image image = new Image(getClass().getResourceAsStream("/Img/img1.png"));
            stage.getIcons().add(image);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void backButtonOnAction(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Dashboardform.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
