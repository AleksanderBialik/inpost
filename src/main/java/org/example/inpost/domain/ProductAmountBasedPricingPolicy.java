package org.example.inpost.domain;

import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.experimental.SuperBuilder;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Value
@SuperBuilder
@Document
public class ProductAmountBasedPricingPolicy extends PricingPolicyAbstract {

    @NotNull Integer minProductsCount;

    @Override
    public BigDecimal calculateDiscountWithTax(@NotNull Product product, @NotNull Integer amount) {
        return null;
    }

    @Override
    public BigDecimal calculateDiscountWithoutTax(@NotNull Product product, @NotNull Integer amount) {
        return null;
    }
}
