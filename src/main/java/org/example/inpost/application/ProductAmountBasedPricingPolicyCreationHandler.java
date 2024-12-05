package org.example.inpost.application;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.inpost.common.PolicyCalculationType;
import org.example.inpost.common.PolicyType;
import org.example.inpost.domain.PricingPolicyRepository;
import org.example.inpost.domain.ProductAmountBasedPricingPolicy;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ProductAmountBasedPricingPolicyCreationHandler {

    private final PricingPolicyRepository pricingPolicyRepository;

    public UUID handle(CreateProductAmountBasedPricingPolicyCreationCommand command) {
        var id = UUID.randomUUID();
        var pricingPolicy = ProductAmountBasedPricingPolicy.builder()
                .id(id)
                .minProductsCount(command.minProductsCount)
                .minProductsPriceBeforeTax(command.minProductsPriceBeforeTax)
                .calculationType(command.calculationType)
                .type(command.type)
                .canBeAppliedWithOtherPolicies(command.canBeAppliedWithOtherPolicies)
                .value(command.value)
                .name(command.name)
                .build();
        pricingPolicyRepository.save(pricingPolicy);
        return id;
    }


    @Builder
    @Data
    public static class CreateProductAmountBasedPricingPolicyCreationCommand {

        @NotNull String name;
        @NotNull BigDecimal value;
        @NotNull Integer minProductsCount;
        @NotNull BigDecimal minProductsPriceBeforeTax;
        @NotNull Boolean canBeAppliedWithOtherPolicies;
        @NotNull PolicyCalculationType calculationType;
        @NotNull PolicyType type;
    }

}
