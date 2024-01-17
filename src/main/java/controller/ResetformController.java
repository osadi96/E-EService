package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import dto.Tm.UserTm;
import java.util.Map;


public class ResetformController {

    public JFXTextField txtOTP;
    public JFXTextField txtEmail;

    private Map<String, UserTm> userDatabase;
    private Map<String, String> otpDatabase;


    public void sendotpButtonOnAction(ActionEvent event) {
        String enteredEmail = txtEmail.getText();
        UserTm userTm = userDatabase.get(enteredEmail);

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

        if (otpDatabase.containsKey(enteredEmail) && otpDatabase.get(enteredEmail).equals(enteredOtp)) {
            String newPassword = generetenewpassword();
            System.out.println("New Password for " + enteredEmail + ": " + newPassword);

            userDatabase.get(enteredEmail).setPassword(newPassword);

            new Alert(Alert.AlertType.INFORMATION, "Password reset successful.").show();
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
}
