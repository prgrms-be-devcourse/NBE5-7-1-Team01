package io.binary.coffeenotfound_404.dto;

import io.binary.coffeenotfound_404.domain.OrderItems;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateOrdersRequestDto {
    private String email;
    private String address;
    private String postcode;
    private List<OrderItemsDto> orderItemsList;
}