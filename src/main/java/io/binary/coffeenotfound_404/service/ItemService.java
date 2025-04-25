package io.binary.coffeenotfound_404.service;

import io.binary.coffeenotfound_404.domain.Items;
import io.binary.coffeenotfound_404.dto.ItemRequestDto;
import io.binary.coffeenotfound_404.dao.ItemRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // CREATE
    public void createItem(ItemRequestDto dto) {
        // 이미지 저장
        String imageUrl = saveImage(dto.getImageBase64());

        // 엔티티 생성 및 저장
        Items item = Items.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .category(dto.getCategory())
                .stock(dto.getStock())
                .desc(dto.getDesc())
                .imageUrl(imageUrl)
                .createdAt(LocalDate.now())
                .build();

        itemRepository.save(item);
    }

    // Base64로 넘어온 이미지 문자열을 파일로 저장하는 메서드
    private String saveImage(String base64) {
        if (base64 == null || base64.isEmpty()) {
            throw new IllegalArgumentException("Image data is empty");
        }

        String[] parts = base64.split(",");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid base64 format");
        }

        byte[] imageBytes = Base64.getDecoder().decode(parts[1]);

        String folderPath = "uploads/";
        String fileName = UUID.randomUUID() + ".png";

        try {
            File folder = new File(folderPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(folderPath + fileName);
            fos.write(imageBytes);
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image", e);
        }

        System.out.println("✅ 저장된 파일 경로: " + folderPath + fileName);

        return "/uploads/" + fileName;
    }

    // READ (목록 조회)
    public List<Items> getAllItem() {
        return itemRepository.findAll();
    }

    // READ (단일 조회)
    public Items getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }

    // UPDATE
    public Items updateItem(Long id, ItemRequestDto dto) {
        Items item = itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));

        item.setName(dto.name);
        item.setPrice(dto.price);
        item.setStock(dto.stock);
        item.setCategory(dto.category);
        item.setDesc(dto.desc);
        if (dto.imageBase64 != null && !dto.imageBase64.isEmpty()) {
            String newImageUrl = saveImage(dto.imageBase64);
            item.setImageUrl(newImageUrl);
        }
        return itemRepository.save(item);
    }

    // DELETE
    public void deleteItem(Long id) {
        if (!itemRepository.existsById(id)) {
            throw new RuntimeException("Item not found");
        }
        itemRepository.deleteById(id);
    }
}
