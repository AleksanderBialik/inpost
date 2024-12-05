package org.example.inpost.application.command;

import lombok.Builder;
import org.example.inpost.common.DiscountLevel;
import org.example.inpost.common.PolicyCalculationType;
import org.example.inpost.common.PolicyType;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record CreateProductQuantityBasedPricingPolicyCreationCommand(@NotNull String name,
                                                                     @NotNull BigDecimal minProductsPriceBeforeTax,
                                                                     @NotNull Boolean canBeAppliedWithOtherPolicies,
                                                                     @NotNull PolicyCalculationType calculationType,
                                                                     @NotNull PolicyType type,
                                                                     @NotNull List<DiscountLevel> discountLevels) {

}
