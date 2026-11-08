package com.rigo.dev.technical_accenture.api.dto.request.update;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductStockUpdateRequest {

    @NotNull(message = "New stock is required")
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stock;
}