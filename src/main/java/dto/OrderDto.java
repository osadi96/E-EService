package dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrderDto {
    private String orderId;
    private String custId;
    private List<OrderDetailsDto> list;
}
