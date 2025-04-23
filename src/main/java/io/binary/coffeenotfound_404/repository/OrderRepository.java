package io.binary.coffeenotfound_404.repository;

import io.binary.coffeenotfound_404.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByEmail(String email);
}
