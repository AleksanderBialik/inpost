package org.example.inpost.infrastructure.rest;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.example.inpost.common.PolicyCalculationType;
import org.example.inpost.common.PolicyType;

import java.math.BigDecimal;
import java.util.List;

public record ProductAmountBasedPricingPolicyRequest(@NotNull @Size(min = 1, max = 255) String name,
                                                     @NotNull @Min(value = 0) BigDecimal minProductsPriceBeforeTax,
                                                     @NotNull boolean canBeAppliedWithOtherPolicies,
                                                     @NotNull PolicyCalculationType calculationType,
                                                     @NotNull @Valid List<DiscountLevel> discountLevels) implements PricingPolicyRequest {

    @Override
    public PolicyType type() {
        return PolicyType.PRODUCT_QUANTITY;
    }

    @Data
    @Builder

    public static class DiscountLevel {
        @NotNull
        @Min(value = 0)
        Integer minProductsQuantity;
        @NotNull
        @Min(value = 0)
        BigDecimal value;
    }
}
