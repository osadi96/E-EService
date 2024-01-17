package dto;

public class CustomerDto {
    private String id;
    private String name;
    private String emailaddress;
    private String address;

    public CustomerDto(String id, String name, String emailaddress, String address) {
        this.id = id;
        this.name = name;
        this.emailaddress = emailaddress;
        this.address = address;
    }

    public CustomerDto() {
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", emailaddress='" + emailaddress + '\'' +
                ", address=" + address +
                '}';
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
}
