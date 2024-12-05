package org.example.inpost

import org.example.inpost.common.PolicyCalculationType
import org.example.inpost.common.PolicyType
import org.example.inpost.domain.PricingPolicyAbstract
import org.example.inpost.domain.PricingPolicyRepository
import org.example.inpost.domain.ProductQuantityBasedPricingPolicy
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.test.web.reactive.server.WebTestClient

class PricingPolicyControllerIntegrationTest extends IntegrationSpecification {

    @Autowired
    WebTestClient webTestClient

    @Autowired
    PricingPolicyRepository policyRepository

    @Autowired
    MongoTemplate mongoTemplate

    Resource createPolicyRequest = new ClassPathResource("__files/create-policy-request.json")

    def "should create pricing policy"() {

        when: "request is received"
        def response = webTestClient.post()
                .uri(
                        "/v1/pricing-policy"
                )
                .bodyValue(createPolicyRequest)
                .exchange()

        then: "response is CREATED and policy is stored"
        response.expectStatus()
                .isCreated()

        def createdPolicy = mongoTemplate.findAll(ProductQuantityBasedPricingPolicy.class, "pricing_policies").first
        createdPolicy.name == "test"
        createdPolicy.calculationType == PolicyCalculationType.PERCENTAGE
        createdPolicy.minProductsPriceBeforeTax == 1
        createdPolicy.type == PolicyType.PRODUCT_QUANTITY
        createdPolicy.discountLevels.first.minProductsQuantity() == 50
        createdPolicy.discountLevels.first.value() == BigDecimal.valueOf(10)
    }
}
