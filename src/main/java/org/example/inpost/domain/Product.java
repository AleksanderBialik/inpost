package org.example.inpost.domain;

import lombok.Builder;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record Product(@NotNull UUID id, @NotNull BigDecimal price, @NotNull BigDecimal vatPercent,
                      @NotNull String name) {
}
