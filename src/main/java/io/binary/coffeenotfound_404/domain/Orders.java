package io.binary.coffeenotfound_404.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Orders {

    @Id
    @Column(name = "orders_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Setter
    private String address;

    @Setter
    private String postcode;

    private  LocalDateTime orderedAt;

    // 객체가 저장되기 전 orderedAt 필드에 현재 시간을 자동으로 설정
    @PrePersist
    private void setOrderedAt() {
        this.orderedAt = LocalDateTime.now();
    }

    @Setter
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrdersItems> ordersItems;

    @Builder
    public Orders(String email, String address, String postcode, List<OrdersItems> ordersItems) {
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.ordersItems = ordersItems;
    }
}
