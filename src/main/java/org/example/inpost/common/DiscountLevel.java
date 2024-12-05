package org.example.inpost.common;

import lombok.Builder;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

@Builder
public record DiscountLevel(@NotNull Integer minProductsQuantity,
                            @NotNull BigDecimal value) {

}
