package dto.Tm;

public class UserTm {
    private String email;
    private String password;


    public UserTm(String email, String password) {
        this.email = email;
        this.password = password;
    }


    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {

    }
}
