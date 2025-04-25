package io.binary.coffeenotfound_404.dao;

import io.binary.coffeenotfound_404.domain.Items;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Items, Long> {


}
