package io.binary.coffeenotfound_404.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

}
