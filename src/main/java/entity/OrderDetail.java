package entity;

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Entity
public class OrderDetail {
    @EmbeddedId
    private OrderDetailsKey id;

    @ManyToOne
  //  @MapsId("code")
    @JoinColumn(name = "item_code")
    Item item;

    @ManyToOne
  //  @MapsId("OrderId")
    @JoinColumn(name = "order_id")
    Orders orders;

    private int qty;
    private double unitPrice;
}
