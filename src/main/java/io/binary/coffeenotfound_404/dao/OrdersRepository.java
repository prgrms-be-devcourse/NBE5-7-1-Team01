package io.binary.coffeenotfound_404.dao;

import io.binary.coffeenotfound_404.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByEmail(String email);
    List<Orders> findByCreatedAtBeforeAndShippedFalse(LocalDateTime time);
}