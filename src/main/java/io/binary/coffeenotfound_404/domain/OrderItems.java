package io.binary.coffeenotfound_404.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItems {

    @Id
    @Column(name = "order_items_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "orders_id")
    private Orders orders;

    @Setter
    @ManyToOne
    @JoinColumn(name = "items_id")
    private Items items;
    @Setter
    private Integer quantity;

    public Integer getItemTotalPrice() {
        return quantity * items.getPrice();
    }

    @Builder
    public OrderItems(Orders orders, Items items, Integer quantity) {
        this.orders = orders;
        this.items = items;
        this.quantity = quantity;
    }
}
