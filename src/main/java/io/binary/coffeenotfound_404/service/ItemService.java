package io.binary.coffeenotfound_404.service;

import io.binary.coffeenotfound_404.domain.Items;
import io.binary.coffeenotfound_404.repository.ItemRepository;
import org.springframework.stereotype.Service;

// 이미지 저장 경로 가져와야 하고

@Service
public class ItemService {
   private ItemRepository itemRepository;

   public ItemService(ItemRepository itemRepository) {
       this.itemRepository = itemRepository;
   }

   public Items createItem(Items item) {
       return itemRepository.save(item);
   }

   public Items getItemById(Long id) {
       return itemRepository.findById(id)
               .orElseThrow(()->new RuntimeException("Item not found"));
   }

   public Items updateItem(Long id, Items updatedItem) {
       Items item = itemRepository.findById(id)
               .orElseThrow(()->new RuntimeException("Item not found"));
       item.setName(updatedItem.getName());
       item.setPrice(updatedItem.getPrice());
       item.setStock(updatedItem.getStock());
       item.setDesc(updatedItem.getDesc());
       item.setImageUrl(updatedItem.getImageUrl());
       item.setCreatedAt(updatedItem.getCreatedAt());
       return itemRepository.save(item);
   }

   public void deleteItem(Long id) {
       Items item = itemRepository.findById(id)
               .orElseThrow(()->new RuntimeException("Item not found"));
       itemRepository.deleteById(id);
   }

}
