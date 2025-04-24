package io.binary.coffeenotfound_404.dto;

import io.binary.coffeenotfound_404.domain.Items;

import java.time.LocalDate;

public class ItemResponseDto {
    public Long id;
    public String name;
    public Integer price;
    public String category;
    public Integer stock;
    public String desc;
    public String imageUrl;
    public LocalDate createdAt;

    public ItemResponseDto(Items item) {
        this.id = item.getId();
        this.name = item.getName();
        this.price = item.getPrice();
        this.category = item.getCategory();
        this.stock = item.getStock();
        this.desc = item.getDesc();
        this.imageUrl = item.getImageUrl();
        this.createdAt = item.getCreatedAt();
    }
}
