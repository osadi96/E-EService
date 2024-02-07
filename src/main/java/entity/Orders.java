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

    public class Orders {
       @ID
        private String orderId;
        private String date;

        @ManyToOne
        @JoinColumn(name = "customer_name")
        private Customer customer;

        @OneToMany(mappedBy = "orders")
        private List<OrderDetail> orderDetails = new ArrayList<>();

        public Orders(String orderId, String date) {
            this.orderId = orderId;
            this.date = date;
        }
    }

