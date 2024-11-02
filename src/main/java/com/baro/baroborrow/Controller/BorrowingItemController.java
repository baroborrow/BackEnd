package com.baro.baroborrow.Controller;

import com.baro.baroborrow.Service.BorrowingItemService;
import com.baro.baroborrow.domain.Item.BorrowingItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowing-items")
public class BorrowingItemController {

    @Autowired
    private BorrowingItemService borrowingItemService;

    @PostMapping
    public ResponseEntity<String> addBorrowingItem(@RequestBody BorrowingItem item) {
        borrowingItemService.addBorrowingItem(item);
        return ResponseEntity.status(HttpStatus.CREATED).body("빌리기 상품 추가 성공");
    }

    @GetMapping
    public ResponseEntity<List<BorrowingItem>> getBorrowingItems() {
        try {
            List<BorrowingItem> items = borrowingItemService.getBorrowingItems();
            return ResponseEntity.ok(items);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<String> deleteBorrowingItem(@PathVariable String itemId) {
        borrowingItemService.deleteBorrowingItem(itemId);
        return ResponseEntity.ok("빌리기 상품 삭제 성공");
    }
}