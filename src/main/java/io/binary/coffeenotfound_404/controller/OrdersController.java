package io.binary.coffeenotfound_404.controller;

import io.binary.coffeenotfound_404.domain.Orders;
import io.binary.coffeenotfound_404.dto.CreateOrdersRequestDTO;
import io.binary.coffeenotfound_404.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;

    @PostMapping
    public ResponseEntity<Long> createOrder(@RequestBody CreateOrdersRequestDTO dto){
        Orders saved = ordersService.save(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved.getId());
    }
}
