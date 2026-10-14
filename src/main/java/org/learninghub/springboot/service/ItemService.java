package org.learninghub.springboot.service;

import org.learninghub.springboot.dto.CreateItemRequest;
import org.learninghub.springboot.model.Item;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ItemService {
    private final Map<String, Item> items = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public ItemService() {
        items.put("1", new Item(
            "1",
            "Master Spring Boot 3 & Virtual Threads",
            "Understand Loom concurrency and Spring Framework 6 architectural foundations",
            "COMPLETED",
            Instant.now()
        ));
    }

    public Collection<Item> findAll() {
        return items.values();
    }

    public Optional<Item> findById(String id) {
        return Optional.ofNullable(items.get(id));
    }

    public Item create(CreateItemRequest request) {
        String id = String.valueOf(counter.incrementAndGet());
        String status = request.status() != null ? request.status() : "PENDING";
        Item item = new Item(id, request.title(), request.description(), status, Instant.now());
        items.put(id, item);
        return item;
    }

    public boolean delete(String id) {
        return items.remove(id) != null;
    }
}
