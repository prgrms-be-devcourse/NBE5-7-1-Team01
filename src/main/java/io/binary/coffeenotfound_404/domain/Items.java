package io.binary.coffeenotfound_404.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Items {
    @Id
    @Column(name = "Item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer price;
    private String category;
    private Integer stock;
    private String desc;
    private String imageUrl;
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
