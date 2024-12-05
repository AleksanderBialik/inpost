package org.example.inpost.infrastructure.rest;

import lombok.Builder;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record CalculationResponse(@NotNull UUID productId,
                                  @NotNull String productName,
                                  @NotNull Integer productCount,
                                  @NotNull BigDecimal productBasePrice,
                                  @NotNull BigDecimal productBasePriceWithTax,
                                  @NotNull BigDecimal productDiscountedPrice,
                                  @NotNull BigDecimal productDiscountedPriceWithTax,
                                  @NotNull BigDecimal productsDiscountedPrice,
                                  @NotNull BigDecimal productsDiscountedPriceWithTax) {
}
