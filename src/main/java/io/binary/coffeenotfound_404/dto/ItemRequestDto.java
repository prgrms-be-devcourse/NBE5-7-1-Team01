package io.binary.coffeenotfound_404.dto;

public class ItemRequestDto {
    public String name;
    public Integer price;
    public String category;
    public Integer stock;
    public String desc;
    public String imageUrl;

    @Override
    public String toString() {
        return "ItemRequestDto{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", stock=" + stock +
                ", desc='" + desc + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}


