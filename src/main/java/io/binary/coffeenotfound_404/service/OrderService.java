package io.binary.coffeenotfound_404.service;

import io.binary.coffeenotfound_404.domain.Order;
import io.binary.coffeenotfound_404.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;


}
