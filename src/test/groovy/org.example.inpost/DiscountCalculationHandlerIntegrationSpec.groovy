package org.example.inpost

import org.example.inpost.application.DiscountCalculationHandler
import org.example.inpost.domain.PricingPolicyRepository
import org.example.inpost.domain.ProductRepository
import org.springframework.beans.factory.annotation.Autowired

class DiscountCalculationHandlerIntegrationSpec extends IntegrationSpecification {


    @Autowired
    PricingPolicyRepository pricingPolicyRepository

    ProductRepository productRepository = Mock()

    DiscountCalculationHandler handler


    def setup() {
        handler = new DiscountCalculationHandler(
                productRepository,
                pricingPolicyRepository
        )
    }

    def "should calculate discount properly"() {
        given: "flat and percentage policies"
        def flatPolicyId = UUID.randomUUID()
        pricingPolicyRepository.save(ProductQuantityBasedPricingPolicyFixture.policy([id: flatPolicyId]))
        def percentagePolicyId = UUID.randomUUID()
        pricingPolicyRepository.save(ProductQuantityBasedPricingPolicyFixture.policy([id: percentagePolicyId]))


        and: "mocked product response"
        def product = ProductFixture.product([:])
        productRepository.findById(_ as UUID) >> Optional.of(product)

        when: "handler is called"
        def command = new DiscountCalculationHandler.CalculateDiscountCommand(product.id(), [flatPolicyId, percentagePolicyId], 10)
        def response = handler.handle(command)

        then: "calculation is correct"
        response.productBasePrice() == BigDecimal.ONE
        response.productId() == product.id()
        response.productName() == product.name()
        response.productQuantity() == 10
        response.totalDiscountedPrice() == BigDecimal.valueOf(8)
        response.totalDiscountedPriceWithTax() == BigDecimal.valueOf(10.30)

    }
}
