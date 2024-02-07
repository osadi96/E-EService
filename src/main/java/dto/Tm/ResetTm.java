package dto.Tm;

public class ResetTm {
    private String otp;
    private String email;

    public ResetTm(String otp, String email) {
        this.otp = otp;
        this.email = email;
    }

    public String toString() {
        return "Employee{" +
                "email='" + email + '\'' +
                " otp='" + otp +
                '}';
    }

    public String getOtp() {
        return otp;
    }
    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
