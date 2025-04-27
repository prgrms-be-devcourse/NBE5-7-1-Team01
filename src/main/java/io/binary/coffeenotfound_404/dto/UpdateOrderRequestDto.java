package io.binary.coffeenotfound_404.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UpdateOrderRequestDto {
    private String address;
    private String postCode;
    private List<OrderItemsDto> orderItemsList;
}
