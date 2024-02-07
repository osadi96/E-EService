package controller;

import com.jfoenix.controls.JFXTextField;
import dto.AdminDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;

public class AdminformController {
    public AnchorPane pane;
    public JFXTextField txtenteremail;
    public static JFXTextField txtenterpassword;
    public JFXTextField txtentername;

    public void createButtonOnAction(ActionEvent event) {
        AdminDto c = new AdminDto(
                txtentername.getText(),
                txtenteremail.getText(),
                txtenterpassword.getText()
        );

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/eeservice", "root", "shivika123");

            String sql = "INSERT INTO employee (id,name, email, password) VALUES (?,?, ?, ?)";

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, c.getId() != null ? c.getId() : "");
                pstmt.setString(2, c.getName() != null ? c.getName() : "");
                pstmt.setString(3, c.getEmail() != null ? c.getEmail() : "");
                pstmt.setString(4, c.getPassword() != null ? c.getPassword() : "");

                int result = pstmt.executeUpdate();

                String password = txtenterpassword.getText();
                if (password.length() >= 8 && containsNumbersLettersAndSymbols(password)) {
                    new Alert(Alert.AlertType.INFORMATION, "Successfully Created!").show();
                } else {
                    new Alert(Alert.AlertType.INFORMATION, "Password should be 8 characters or more, which includes numbers, letters, and symbols").show();
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean containsNumbersLettersAndSymbols(String password) {
        return password.matches(".*\\d.*") && password.matches(".*[a-zA-Z].*") && password.matches(".*[!@#$%^&*()].*");
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
