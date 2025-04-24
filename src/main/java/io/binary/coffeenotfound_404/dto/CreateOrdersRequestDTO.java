package io.binary.coffeenotfound_404.dto;

import io.binary.coffeenotfound_404.domain.OrdersItems;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreateOrdersRequestDTO {
    private String email;
    private String address;
    private String postcode;
    private List<OrdersItems> ordersItems;
}
