package io.binary.coffeenotfound_404.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    private String address;

    private String postcode;

    private LocalDateTime orderedAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItemList;

    @Builder

    public Order(Long id, String email, String address, String postcode, LocalDateTime orderedAt, List<OrderItem> orderItemList) {
        this.id = id;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.orderedAt = orderedAt;
        this.orderItemList = orderItemList;
    }
}
