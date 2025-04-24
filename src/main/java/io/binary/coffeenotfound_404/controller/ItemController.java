package io.binary.coffeenotfound_404.controller;

import io.binary.coffeenotfound_404.domain.Items;
import io.binary.coffeenotfound_404.dto.ItemRequestDto;
import io.binary.coffeenotfound_404.dto.ItemResponseDto;
import io.binary.coffeenotfound_404.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
//CRUD Controller
public class ItemController {
    private final ItemService itemService;


    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    //생성
    @PostMapping
    public ResponseEntity<ItemResponseDto> createItem(@RequestBody ItemRequestDto dto) {
        //DTO -> Service
        Items saveItem = itemService.createItem(dto);
        return ResponseEntity.ok(new ItemResponseDto(saveItem));

    }


    //조회
    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDto> getItemById(@PathVariable Long id) {
        Items item = itemService.getItemById(id);
        return ResponseEntity.ok(new ItemResponseDto(item));
    }

    //수정
    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDto> updateItem(@PathVariable Long id, @RequestBody ItemRequestDto dto) {
        //DTO -> Service
        Items updateItem = itemService.updateItem(id, dto);
        return ResponseEntity.ok(new ItemResponseDto(updateItem));
    }

    //삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Items> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }


}
