package com.baro.baroborrow.Controller;

import com.baro.baroborrow.Service.LendingItemService;
import com.baro.baroborrow.domain.Item.LendingItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lending-items")
public class LendingItemController {

    @Autowired
    private LendingItemService lendingItemService;


    @PostMapping
    public ResponseEntity<String> addLendingItem(@RequestBody LendingItem item) {
        lendingItemService.addLendingItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body("빌려주기 상품 등록 성공");
    }

    @GetMapping
    public ResponseEntity<List<LendingItem>> getLendingItems() {
        try {
            List<LendingItem> items = lendingItemService.getLendingItems();
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @DeleteMapping("/{itemId}")
    public ResponseEntity<String> deleteLendingItem(@PathVariable String itemId) {
        lendingItemService.deleteLendingItem(itemId);
        return ResponseEntity.ok("빌려주기 상품 삭제 성공");
    }
}

