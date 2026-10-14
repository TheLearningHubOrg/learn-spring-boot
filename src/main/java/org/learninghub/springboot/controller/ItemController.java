package org.learninghub.springboot.controller;

import jakarta.validation.Valid;
import org.learninghub.springboot.dto.CreateItemRequest;
import org.learninghub.springboot.model.Item;
import org.learninghub.springboot.service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<Collection<Item>> getAllItems() {
        return ResponseEntity.ok(itemService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable String id) {
        return itemService.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body((Item) null));
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@Valid @RequestBody CreateItemRequest request) {
        Item created = itemService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteItem(@PathVariable String id) {
        boolean deleted = itemService.delete(id);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("success", false, "error", "Item not found"));
        }
        return ResponseEntity.ok(Map.of("success", true, "deletedId", id));
    }
}
