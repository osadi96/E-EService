package dto.Tm;

import javafx.scene.control.Button;

public class CustomerTm {
    private String id;
    private String name;
    private String emailaddress;
    private String address;
    private Button btn;

    public CustomerTm() {
    }

    public CustomerTm(String id, String name, String emailaddress,String address, Button btn) {
        this.id = id;
        this.name = name;
        this.emailaddress = emailaddress;
        this.address = address;
        this.btn = btn;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "CustomerTm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", emailaddress='" + emailaddress + '\'' +
                ", address=" + address +
                ", btn=" + btn +
                '}';
    }
}
