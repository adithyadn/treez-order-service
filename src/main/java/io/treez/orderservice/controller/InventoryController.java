package io.treez.orderservice.controller;

import io.treez.orderservice.model.Product;
import io.treez.orderservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventories")
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts() {

        return ResponseEntity.ok(inventoryService.listAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductByID(@PathVariable(name = "id") Long productId) {
        return ResponseEntity.ok(inventoryService.findProductById(productId));
    }

    @PostMapping("/")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(inventoryService.createProduct(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable(name = "id") long productId
            , @RequestBody Product product) {
        return ResponseEntity.ok(inventoryService.updateProduct(productId, product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable(name = "id") long productId) {

        inventoryService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
