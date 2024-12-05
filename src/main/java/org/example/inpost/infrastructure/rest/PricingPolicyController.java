package org.example.inpost.infrastructure.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.inpost.application.ProductAmountBasedPricingPolicyCreationHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/pricing-policy")
public class PricingPolicyController {

    private final ProductAmountBasedPricingPolicyCreationHandler productAmountBasedPricingPolicyCreationHandler;


    @PostMapping()
    public ResponseEntity<IdResponse> createPricingPolicy(@RequestBody @Valid PricingPolicyRequest request) {

        UUID resourceId = switch (request) {
            case ProductAmountBasedPricingPolicyRequest req -> {
                var command = ProductAmountBasedPricingPolicyCreationHandler.CreateProductAmountBasedPricingPolicyCreationCommand
                        .builder()
                        .name(req.name())
                        .minProductsPriceBeforeTax(req.minProductsPriceBeforeTax())
                        .type(req.type())
                        .value(req.value())
                        .canBeAppliedWithOtherPolicies(req.canBeAppliedWithOtherPolicies())
                        .calculationType(req.calculationType())
                        .minProductsCount(req.minProductsCount())
                        .build();
                yield productAmountBasedPricingPolicyCreationHandler.handle(command);
            }
            default -> throw new IllegalStateException("Unknown type: " + request.type().toString());
        };
        return ResponseEntity.status(HttpStatus.CREATED).body(new IdResponse(resourceId));
    }
}
