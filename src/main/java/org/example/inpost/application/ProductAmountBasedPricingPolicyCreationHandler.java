package org.example.inpost.application;

import lombok.RequiredArgsConstructor;
import org.example.inpost.application.command.CreateProductQuantityBasedPricingPolicyCreationCommand;
import org.example.inpost.domain.PricingPolicyRepository;
import org.example.inpost.domain.ProductQuantityBasedPricingPolicy;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ProductAmountBasedPricingPolicyCreationHandler {

    private final PricingPolicyRepository pricingPolicyRepository;

    public UUID handle(CreateProductQuantityBasedPricingPolicyCreationCommand command) {
        var id = UUID.randomUUID();
        var pricingPolicy = ProductQuantityBasedPricingPolicy.builder()
                .id(id)
                .minProductsPriceBeforeTax(command.minProductsPriceBeforeTax())
                .calculationType(command.calculationType())
                .type(command.type())
                .canBeAppliedWithOtherPolicies(command.canBeAppliedWithOtherPolicies())
                .name(command.name())
                .discountLevels(command.discountLevels())
                .build();
        pricingPolicyRepository.save(pricingPolicy);
        return id;
    }


}
