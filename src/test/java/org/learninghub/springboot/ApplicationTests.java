package org.learninghub.springboot;

import org.junit.jupiter.api.Test;
import org.learninghub.springboot.dto.CreateItemRequest;
import org.learninghub.springboot.model.Item;
import org.learninghub.springboot.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationTests {

    @Autowired
    private ItemService itemService;

    @Test
    void contextLoads() {
        assertNotNull(itemService);
    }

    @Test
    void testItemCreationAndRetrieval() {
        CreateItemRequest request = new CreateItemRequest(
            "Test Spring Boot Item",
            "Validating service logic",
            "IN_PROGRESS"
        );

        Item created = itemService.create(request);
        assertNotNull(created.id());
        assertEquals("Test Spring Boot Item", created.title());

        var found = itemService.findById(created.id());
        assertTrue(found.isPresent());
        assertEquals("Test Spring Boot Item", found.get().title());
    }
}
