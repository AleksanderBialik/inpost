package org.example.inpost.infrastructure.rest;

import lombok.Builder;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record CalculationResponse(@NotNull UUID productId,
                                  @NotNull String productName,
                                  @NotNull Integer productQuantity,
                                  @NotNull BigDecimal productBasePrice,
                                  @NotNull BigDecimal totalDiscountedPrice,
                                  @NotNull BigDecimal totalDiscountedPriceWithTax) {
}
