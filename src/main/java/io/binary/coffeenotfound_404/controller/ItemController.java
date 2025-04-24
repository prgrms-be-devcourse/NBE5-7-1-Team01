package io.binary.coffeenotfound_404.controller;

import io.binary.coffeenotfound_404.domain.Items;
import io.binary.coffeenotfound_404.dto.ItemRequestDto;
import io.binary.coffeenotfound_404.dto.ItemResponseDto;
import io.binary.coffeenotfound_404.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/items")
@Slf4j

//CRUD Controller
public class ItemController {
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    //아이템 생성 (POST /items)
    @PostMapping
    public ResponseEntity<ItemResponseDto> createItem(@RequestBody ItemRequestDto dto) {
        //DTO -> Service
        Items saveItem = itemService.createItem(dto);

        return ResponseEntity.ok(new ItemResponseDto(saveItem));

    }

    //전체 목록 조회 (GET /Items)
    @GetMapping
    public ResponseEntity<List<ItemResponseDto>> getAllItem() {
        List<Items> items = itemService.getAllItem();
        List<ItemResponseDto> result = items.stream()
                                            .map(ItemResponseDto::new)
                                            .toList();
        return ResponseEntity.ok(result);
    }

    //아이템 수정 (PUT /Items/id)
    @PutMapping("/{id}")
    public ResponseEntity<ItemResponseDto> updateItem(@PathVariable Long id, @RequestBody ItemRequestDto dto) {
        //DTO -> Service
        Items updateItem = itemService.updateItem(id, dto);
        return ResponseEntity.ok(new ItemResponseDto(updateItem));
    }

    //삭제 (DELETE /Items/id)
    @DeleteMapping("/{id}")
    public ResponseEntity<Items> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
