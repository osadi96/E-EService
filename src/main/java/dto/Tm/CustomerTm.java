package dto.Tm;

public class CustomerTm {
    private String name;
    private String emailaddress;
    private String address;
    private String orderid;

    public CustomerTm(String name, String emailaddress, String address, String orderid) {
        this.name = name;
        this.emailaddress = emailaddress;
        this.address = address;
        this.orderid = orderid;
    }

    public CustomerTm() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", emailaddress='" + emailaddress + '\'' +
                ", address='" + address + '\'' +
                ", orderid=" + orderid +
                '}';
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

    public String getOrderid() {
        return orderid;
    }
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
}

