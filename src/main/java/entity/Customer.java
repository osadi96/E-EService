package entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity

public class Customer {
    @ID
    private String name;
    private String emailaddress;
    private String address;
    private String orderId;

    @OneToMany(mappedBy = "customer")
    private List<Orders> orders = new ArrayList<>();

    public Customer(String name, String emailaddress, String address, String orderId) {
        this.name = name;
        this.emailaddress = emailaddress;
        this.address = address;
        this.orderId = orderId;
    }
}
