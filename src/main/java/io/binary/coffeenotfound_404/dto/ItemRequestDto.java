package io.binary.coffeenotfound_404.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class ItemRequestDto {
    public String name;
    public Integer price;
    public String category;
    public Integer stock;
    public String desc;
    public String imageBase64;

    public LocalDate createdAt;

    public ItemRequestDto(String name, Integer price, String category, Integer stock, String desc, String imageBase64, LocalDate createdAt) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.stock = stock;
        this.desc = desc;
        this.imageBase64 = imageBase64;
        this.createdAt = createdAt;
    }
}


