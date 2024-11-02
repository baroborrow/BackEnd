package com.baro.baroborrow.Controller;

import com.baro.baroborrow.DTO.ProductAddDTO;
import com.baro.baroborrow.Domain.Product;
import com.baro.baroborrow.Service.ProductService;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreException;
import com.google.firebase.cloud.FirestoreClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/add-product")
    public void addProduct(@RequestBody ProductAddDTO productAddDTO) throws Exception {
        Product product = new Product(productAddDTO.getBoard_id(),productAddDTO.getUser_id(),productAddDTO.getUser_id2(),productAddDTO.getCategory(),productAddDTO.getBorrow_date(),productAddDTO.getReturn_date());
        productService.addProduct(product);
    }

    @PatchMapping("/product-return/{product_id}")
    public void returnProduct(@PathVariable String product_id) throws Exception {
        productService.returnProduct(product_id);
    }

    @GetMapping("/product/{user_id}")
    public ResponseEntity<List<Product>> getProduct(@PathVariable String user_id) throws Exception {
        List<Product> products = productService.getProduct(user_id);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product2/{user_id}")
    public ResponseEntity<List<Product>> getProduct2(@PathVariable String user_id) throws Exception {
        List<Product> products = productService.getProduct2(user_id);
        return ResponseEntity.ok(products);
    }
}
