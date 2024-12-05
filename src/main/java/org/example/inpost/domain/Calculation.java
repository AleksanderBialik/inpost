package org.example.inpost.domain;

import lombok.Builder;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record Calculation(@NotNull UUID productId,
                          @NotNull String productName,
                          @NotNull Integer productQuantity,
                          @NotNull BigDecimal productBasePrice,
                          @NotNull BigDecimal totalDiscountedPrice,
                          @NotNull BigDecimal totalDiscountedPriceWithTax) {
}
