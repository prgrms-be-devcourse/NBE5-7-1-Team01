package io.binary.coffeenotfound_404.controller;

import io.binary.coffeenotfound_404.domain.Items;
import io.binary.coffeenotfound_404.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
public class ItemController {
    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }
    //생성
    @PostMapping
    public ResponseEntity<Items> createItem(@RequestBody Items items) {
        return ResponseEntity.ok(itemService.createItem(items));
    }

    //조회
    @GetMapping("/{id}")
    public ResponseEntity<Items> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    //수정
    @PutMapping("/{id}")
    public ResponseEntity<Items> updateItem(@PathVariable Long id, @RequestBody Items updatedItem) {
        return ResponseEntity.ok(itemService.updateItem(id, updatedItem));
    }
    //삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Items> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }


}
