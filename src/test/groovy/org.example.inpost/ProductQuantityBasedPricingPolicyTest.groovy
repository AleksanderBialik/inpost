package org.example.inpost

import org.example.inpost.common.PolicyCalculationType
import spock.lang.Specification

class ProductQuantityBasedPricingPolicyTest extends Specification {

    def "should calculate FLAT discount"() {
        given:
        def product = ProductFixture.product(value: BigDecimal.valueOf(100))
        def policy = ProductQuantityBasedPricingPolicyFixture.policy(calculationType: PolicyCalculationType.FLAT)

        when:
        def result = policy.calculateDiscount(BigDecimal.valueOf(100), 1, product)
        then:
        result == BigDecimal.ONE
    }

    def "should calculate PERCENTAGE discount"() {
        given:
        def product = ProductFixture.product(value: BigDecimal.valueOf(100))
        def discountLevel = ProductQuantityBasedPricingPolicyFixture.discountLevel(value: BigDecimal.valueOf(10))
        def policy = ProductQuantityBasedPricingPolicyFixture.policy(calculationType: PolicyCalculationType.PERCENTAGE, discountLevels: [discountLevel])

        when:
        def result = policy.calculateDiscount(BigDecimal.valueOf(100), 1, product)
        then:
        result == BigDecimal.valueOf(10)
    }

    def "should not calculate discount when discount levels are empty"() {
        given:
        def product = ProductFixture.product(value: BigDecimal.valueOf(100))
        def policy = ProductQuantityBasedPricingPolicyFixture.policy(calculationType: PolicyCalculationType.PERCENTAGE, discountLevels: [])

        when:
        def result = policy.calculateDiscount(BigDecimal.valueOf(100), 1, product)
        then:
        result == BigDecimal.ZERO
    }

    def "should not calculate discount when product's price is smaller than discount's minProductsPriceBeforeTax"() {
        given:
        def product = ProductFixture.product(value: BigDecimal.valueOf(99))
        def policy = ProductQuantityBasedPricingPolicyFixture.policy(calculationType: PolicyCalculationType.PERCENTAGE, minProductsPriceBeforeTax: BigDecimal.valueOf(100))

        when:
        def result = policy.calculateDiscount(BigDecimal.valueOf(100), 1, product)
        then:
        result == BigDecimal.ZERO
    }

    def "should calculate the discount and use the highest possible discount level"() {
        given:
        def product = ProductFixture.product(value: BigDecimal.valueOf(100))
        def discountLevel1 = ProductQuantityBasedPricingPolicyFixture.discountLevel(value: BigDecimal.valueOf(10), minProductsQuantity: 1)
        def discountLevel2 = ProductQuantityBasedPricingPolicyFixture.discountLevel(value: BigDecimal.valueOf(20), minProductsQuantity: 5)
        def policy = ProductQuantityBasedPricingPolicyFixture.policy(calculationType: PolicyCalculationType.PERCENTAGE,discountLevels: [discountLevel1,discountLevel2])

        when:
        def result = policy.calculateDiscount(BigDecimal.valueOf(1000), 5, product)
        then:
        result == BigDecimal.valueOf(200)
    }
}
