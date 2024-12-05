package org.example.inpost.infrastructure.rest;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.inpost.common.PolicyCalculationType;
import org.example.inpost.common.PolicyType;

import java.math.BigDecimal;

public record ProductAmountBasedPricingPolicyRequest(@NotNull @Size(min = 1, max = 255) String name,
                                                     @NotNull BigDecimal value,
                                                     @NotNull @Min(value = 0) BigDecimal minProductsPriceBeforeTax,
                                                     @NotNull @Min(value = 0) Integer minProductsCount,
                                                     @NotNull boolean canBeAppliedWithOtherPolicies,
                                                     @NotNull PolicyCalculationType calculationType) implements PricingPolicyRequest {

    @Override
    public PolicyType type() {
        return PolicyType.PRODUCT_AMOUNT;
    }
}
