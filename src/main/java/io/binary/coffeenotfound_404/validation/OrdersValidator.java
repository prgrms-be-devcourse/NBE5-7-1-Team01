package io.binary.coffeenotfound_404.validation;

import io.binary.coffeenotfound_404.dto.CreateOrdersRequestDto;
import io.binary.coffeenotfound_404.dto.OrderItemsDto;
import io.binary.coffeenotfound_404.exceptions.OrdersException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrdersValidator {

    public void ordersValidate(CreateOrdersRequestDto dto) {
        // 1. 이메일 검사
        if (!isValidEmail(dto.getEmail())) {
            throw new OrdersException.InvalidEmailException();
        }

        // 2. 주소 검사
        if (!isValidAddress(dto.getAddress())) {
            throw new OrdersException.InvalidAddressException();
        }

        // 3. 우편번호 검사
        if (!isValidPostcode(dto.getPostcode())) {
            throw new OrdersException.InvalidPostcodeException();
        }

        // 4. 주문 아이템 검사
        if (!isValidOrderItems(dto.getOrderItemsList())) {
            throw new OrdersException.InvalidOrderItemsException();
        }
    }

    // 이메일 유효성 검사
    private boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email != null && email.matches(emailRegex);
    }

    // 주소 유효성 검사
    private boolean isValidAddress(String address) {
        return address != null && !address.trim().isEmpty();
    }

    // 우편번호 유효성 검사
    private boolean isValidPostcode(String postcode) {
        return postcode != null && !postcode.trim().isEmpty();
    }

    // 주문 아이템 유효성 검사
    private boolean isValidOrderItems(List<OrderItemsDto> ordersItems) {
        return ordersItems != null && !ordersItems.isEmpty();
    }

}