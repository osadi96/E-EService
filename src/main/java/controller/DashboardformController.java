package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DashboardformController {
    public Label lblTime;
    public AnchorPane pane;

    public void initialize() {
        calculateTime();
    }

    private void calculateTime() {
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.ZERO,
                actionEvent -> lblTime.setText(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")))
        ), new KeyFrame(Duration.seconds(1)));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void StoreButtonOnAction(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Storeform.fxml"))));
            stage.setTitle("SYSTEM MANAGMENT");
            Image image = new Image(getClass().getResourceAsStream("/Img/img1.png"));
            stage.getIcons().add(image);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void itemButtonOnAction(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Itemform.fxml"))));
            stage.setTitle("ITEM MANAGMENT");
            Image image = new Image(getClass().getResourceAsStream("/Img/img1.png"));
            stage.getIcons().add(image);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void orderButtonOnAction(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/Orderform.fxml"))));
            stage.setTitle("ORDER MANAGMENT");
            Image image = new Image(getClass().getResourceAsStream("/Img/img1.png"));
            stage.getIcons().add(image);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void reportsButtonOnAction(ActionEvent event) {
    }

}
