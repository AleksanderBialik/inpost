package org.example.inpost

import org.example.inpost.common.DiscountLevel
import org.example.inpost.common.PolicyCalculationType
import org.example.inpost.common.PolicyType
import org.example.inpost.domain.ProductQuantityBasedPricingPolicy


class ProductQuantityBasedPricingPolicyFixture {

    static ProductQuantityBasedPricingPolicy policy(Map args) {
        def defaults = [
                id                           : UUID.randomUUID(),
                name                         : "test",
                minProductsPriceBeforeTax    : 0,
                canBeAppliedWithOtherPolicies: true,
                type                         : PolicyType.PRODUCT_QUANTITY,
                calculationType              : PolicyCalculationType.FLAT,
                discountLevels               : [discountLevel([:])]

        ]
        args = defaults + args
        return ProductQuantityBasedPricingPolicy.builder()
                .id((args.id as UUID) ?: UUID.randomUUID())
                .name((args.name as String) ?: "test")
                .minProductsPriceBeforeTax((args.minProductsPriceBeforeTax as BigDecimal) ?: BigDecimal.ZERO)
                .canBeAppliedWithOtherPolicies((args.canBeAppliedWithOtherPolicies as boolean) ?: true)
                .type((args.type as PolicyType) ?: PolicyType.PRODUCT_QUANTITY)
                .calculationType((args.calculationType as PolicyCalculationType) ?: PolicyCalculationType.FLAT)
                .discountLevels((args.discountLevels as List<DiscountLevel>) ?: [])
                .build()
    }

    static DiscountLevel discountLevel(Map args) {
        new DiscountLevel(
                (args.minProductsQuantity as Integer) ?: 0,
                (args.value as BigDecimal) ?: BigDecimal.ONE,
        )
    }
}