package controller;

import com.jfoenix.controls.JFXTextField;
import dto.Tm.ResetTm;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Map;

public class ResetformController {

    public JFXTextField txtOTP;
    public JFXTextField txtEmail;

    private Map<String, ResetTm> userDatabase;
    private Map<String, String> otpDatabase;


    public void sendotpButtonOnAction(ActionEvent event) {
        String enteredEmail = txtEmail.getText();
        ResetTm userTm = userDatabase.get(enteredEmail);

        if (userTm != null) {
            String otp = generateOtp();
            System.out.println("OTP for " + enteredEmail + ": " + otp);

            otpDatabase.put(enteredEmail, otp);

            new Alert(Alert.AlertType.INFORMATION, "OTP sent to your email.").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Email not found.").show();
        }
    }

    private String generateOtp() {
        return txtOTP.getText();
    }

    public void resetButtonOnAction(ActionEvent event) {
        String enteredEmail = txtEmail.getText();
        String enteredOtp = getEnteredOtp();

        if (otpDatabase.containsKey(enteredEmail) && otpDatabase.get(enteredOtp).equals(enteredOtp)) {
            String newPassword = generetenewpassword();
            System.out.println("New Password for:"+ newPassword);

            userDatabase.get(enteredEmail).setEmail(newPassword);

            new Alert(Alert.AlertType.INFORMATION, "Password reset successful!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid OTP.").show();
        }
    }

    private String getEnteredOtp() {
        return "enteredOtp";
    }

    private String generetenewpassword() {
        return "newPassword";
    }

    private String txtOTP() {
        return null;
    }

    public void backButtonOnAction(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Employee.fxml"))));
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
