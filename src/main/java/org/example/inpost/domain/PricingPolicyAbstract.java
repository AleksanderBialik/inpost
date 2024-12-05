package org.example.inpost.domain;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.example.inpost.common.PolicyCalculationType;
import org.example.inpost.common.PolicyType;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Document(collection = "pricing_policies")
@SuperBuilder
@Getter
public abstract class PricingPolicyAbstract {
    @NotNull UUID id;
    @NotNull String name;
    @NotNull BigDecimal value;
    @NotNull BigDecimal minProductsPriceBeforeTax;
    @NotNull Boolean canBeAppliedWithOtherPolicies;
    @NotNull PolicyType type;
    @NotNull PolicyCalculationType calculationType;

    public abstract BigDecimal calculateDiscountWithTax(@NotNull Product product, @NotNull Integer amount);

    public abstract BigDecimal calculateDiscountWithoutTax(@NotNull Product product, @NotNull Integer amount);
}
