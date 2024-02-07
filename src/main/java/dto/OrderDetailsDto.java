package dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class OrderDetailsDto {
    private String orderId;
    private String itemCode;
    private String iteDesc;
    private int qty;
    private double unitPrice;
}
