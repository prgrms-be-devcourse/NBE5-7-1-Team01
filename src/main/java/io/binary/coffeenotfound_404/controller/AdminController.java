package io.binary.coffeenotfound_404.controller;

import io.binary.coffeenotfound_404.domain.Orders;
import io.binary.coffeenotfound_404.service.OrdersService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
// admin.html 페이지
public class AdminController {
    private final OrdersService ordersService;

    public AdminController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "redirect:/admin.html"; // templates/admin.html
    }

    // 모든 주문 내역 조회 API
    @GetMapping("/admin/orders")
    public ResponseEntity<List<Orders>> getAllOrders() {
        List<Orders> allOrders = ordersService.findAllOrders();

        if (allOrders.isEmpty()) {
            return ResponseEntity.noContent().build(); // 주문이 없으면 204 응답
        }

        return ResponseEntity.ok(allOrders); // 200 OK + JSON
    }
}


