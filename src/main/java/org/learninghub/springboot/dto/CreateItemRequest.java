package org.learninghub.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateItemRequest(
    @NotBlank(message = "Title must not be blank")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    String title,

    @Size(max = 500, message = "Description must not exceed 500 characters")
    String description,

    String status
) {}
