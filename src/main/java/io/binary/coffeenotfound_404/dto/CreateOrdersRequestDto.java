package io.binary.coffeenotfound_404.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateOrdersRequestDto {
    private String email;
    private String address;
    private String postCode;
    private List<OrderItemsDto> orderItemsList;

    private Integer price;
    private Integer totalPrice;
}