package io.binary.coffeenotfound_404.controller;

import io.binary.coffeenotfound_404.domain.Orders;
import io.binary.coffeenotfound_404.dto.CreateOrdersRequestDto;
import io.binary.coffeenotfound_404.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrdersService ordersService;

    @PostMapping
    public ResponseEntity<Long> createOrder(@RequestBody CreateOrdersRequestDto dto){
        Orders saved = ordersService.save(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved.getId());
    }

    @GetMapping("/{email:.+}")
    public ResponseEntity<List<Orders>> getOrdersByEmail(@PathVariable String email) {
        List<Orders> orders = ordersService.findOrdersByEmail(email);

        if (orders.isEmpty()) {            // 주문이 없으면 204 No Content
            return ResponseEntity.noContent().build();
        }

        for (Orders order : orders) {
            log.info("order.toString() = {}", order.toString());
        }

        return ResponseEntity.ok(orders);  // 200 OK + JSON
    }

    @DeleteMapping("/{email:.+}/{orderId}")
    public ResponseEntity<Void> deleteOrdersByEmail(@PathVariable String email, @PathVariable Long orderId) {
        ordersService.removeOrdersByEmail(email, orderId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{email:.+}/{orderId}")
}