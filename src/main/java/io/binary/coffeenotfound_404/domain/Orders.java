package io.binary.coffeenotfound_404.domain;

import jakarta.persistence.*;
import lombok.*;

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

    // 같은 이메일로 여러번 주문 가능
    @Column(nullable = false)
    private String email;

    @Setter
    private String address;

    @Setter
    private String postCode;

    private final LocalDateTime createdAt = LocalDateTime.now();

    @Setter
    private boolean shipped = false;

    @Setter
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> orderItemsList;

    @Builder
    public Orders(String email, String address, String postCode, List<OrderItems> orderItemsList) {
        this.email = email;
        this.address = address;
        this.postCode = postCode;
        this.orderItemsList = orderItemsList;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", postCode='" + postCode + '\'' +
                ", createdAt=" + createdAt +
                ", orderItemsList=" + orderItemsList +
                '}';
    }
}