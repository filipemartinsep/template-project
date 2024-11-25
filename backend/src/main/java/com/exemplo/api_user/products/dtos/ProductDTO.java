package com.exemplo.api_user.products.dtos;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDTO(
        @Id UUID id,
        @NotBlank String name,
        @NotBlank String description,
        @NotNull @Positive BigDecimal price
) {
}
