package io.binary.coffeenotfound_404.service;

import io.binary.coffeenotfound_404.domain.Items;
import io.binary.coffeenotfound_404.dto.ItemRequestDto;
import io.binary.coffeenotfound_404.dao.ItemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

// 이미지 저장 경로 가져와야 하고

@Service
public class ItemService {
   private ItemRepository itemRepository;

   public ItemService(ItemRepository itemRepository) {
       this.itemRepository = itemRepository;
   }

   // CRETAE
   public Items createItem(ItemRequestDto dto) {
       Items item = Items.builder()
               .name(dto.name)
               .price(dto.price)
               .category(dto.category)
               .stock(dto.stock)
               .desc(dto.desc)
               .imageUrl(dto.imageUrl)
               .createdAt(LocalDate.now())
               .build();
       return itemRepository.save(item);
   }

   // 목록 조회
   public List<Items> getAllItem() {
       return itemRepository.findAll();
   }



   public Items updateItem(Long id, ItemRequestDto dto) {
       Items item = itemRepository.findById(id)
               .orElseThrow(()->new RuntimeException("Item not found"));

       item.setName(dto.name);
       item.setPrice(dto.price);
       item.setStock(dto.stock);
       item.setDesc(dto.desc);

       item.setCategory(dto.category);

       // null이면 기존 값 유지
       if (dto.imageUrl != null) {
           item.setImageUrl(dto.imageUrl);
       }

       return itemRepository.save(item);
   }

   public void deleteItem(Long id) {
       Items item = itemRepository.findById(id)
               .orElseThrow(()->new RuntimeException("Item not found"));
       itemRepository.deleteById(id);
   }

    public Items getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
    }
}
