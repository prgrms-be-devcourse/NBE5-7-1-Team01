package io.binary.coffeenotfound_404.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Items {
    @Id
    @Column(name = "Item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private Integer stock;

    @Column(name = "item_desc", nullable = false)
    private String desc;

    @Column(nullable = true)
    private String imageUrl;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Builder
    public Items(String name, Integer price, String category, Integer stock, String desc, String imageUrl, LocalDate createdAt) {

        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;
        this.desc = desc;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
    }


}