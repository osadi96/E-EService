package controller;

import com.jfoenix.controls.JFXTextField;
import dto.AdminDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;
import java.util.Objects;

public class EmployeeformContoller {
    public AnchorPane pane;
    public JFXTextField txtemid;
    public JFXTextField txtemname;
    public JFXTextField txtememail;
    public JFXTextField txtempassword;
    private Connection connection;
    private AdminDto c;

    public void submitButtonOnAction(ActionEvent event) {
        String sql = "INSERT INTO employee VALUES('\" + c.getId() + \"' + '\" + c.getName() +\"' + '\" + c.getEmail() + \"', '\" + c.getPassword() + \"')";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eeservice", "root", "shivika123");
            Statement stm = connection.createStatement();
            int result = stm.executeUpdate(sql);

            String enteredPassword = txtempassword.getText();
            String correctPassword = getPasswordFromDatabase();

            if (!enteredPassword.isEmpty() && enteredPassword.equals(correctPassword)) {
                new Alert(Alert.AlertType.INFORMATION, "Login successful!").show();
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Password is Incorrect!").show();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private String getPasswordFromDatabase() {
        String sql = "SELECT password FROM employee WHERE name = ?";  // Replace with the appropriate condition
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, c.getEmail());
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getString("password");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void passwordButtonOnAction(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/View/Resetform.fxml")))));
            stage.setTitle("PASSWORD RESET BOARD");
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
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Storeform.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

