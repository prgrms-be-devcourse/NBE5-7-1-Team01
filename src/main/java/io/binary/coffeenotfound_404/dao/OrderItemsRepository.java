package io.binary.coffeenotfound_404.dao;

import io.binary.coffeenotfound_404.domain.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {
}
