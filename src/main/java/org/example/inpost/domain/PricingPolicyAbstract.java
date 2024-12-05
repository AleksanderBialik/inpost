package org.example.inpost.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.example.inpost.common.PolicyCalculationType;
import org.example.inpost.common.PolicyType;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.UUID;

@Document(collection = "pricing_policies")
@SuperBuilder
@Data
@NoArgsConstructor
public abstract class PricingPolicyAbstract {
    @NotNull UUID id;
    @NotNull String name;
    @NotNull BigDecimal minProductsPriceBeforeTax;
    @NotNull Boolean canBeAppliedWithOtherPolicies;
    @NotNull PolicyType type;
    @NotNull PolicyCalculationType calculationType;

    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);

    public abstract BigDecimal calculateDiscount(@NotNull BigDecimal currentPrice, @NotNull Integer productQuantity, @NotNull Product product);

}
