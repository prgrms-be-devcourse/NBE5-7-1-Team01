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

    // 단일 상품 조회 (GET /Items)
    @GetMapping("/{id}")
    public ResponseEntity<ItemResponseDto> getItemById(@PathVariable Long id) {
        Items item = itemService.getItemById(id);
        return ResponseEntity.ok(new ItemResponseDto(item));
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
        try {
            Items updateItem = itemService.updateItem(id, dto);
            return ResponseEntity.ok(new ItemResponseDto(updateItem));
        } catch (Exception e) {
            log.error("업데이트 실패: {}", e.getMessage()); // 핵심 에러 메시지 출력
            e.printStackTrace(); // 전체 스택 트레이스 확인 (콘솔에 찍힘)
            return ResponseEntity.internalServerError().body(null);
        }
    }

    //삭제 (DELETE /Items/id)
    @DeleteMapping("/{id}")
    public ResponseEntity<Items> deleteItem(@PathVariable Long id) {
        itemService.deleteItem(id);
        return ResponseEntity.noContent().build();
    }
}
