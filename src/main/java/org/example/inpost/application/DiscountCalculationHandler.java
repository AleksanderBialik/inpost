package org.example.inpost.application;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.inpost.common.NotFoundException;
import org.example.inpost.domain.Calculation;
import org.example.inpost.domain.PricingPolicyAbstract;
import org.example.inpost.domain.PricingPolicyRepository;
import org.example.inpost.domain.Product;
import org.example.inpost.domain.ProductRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DiscountCalculationHandler {
    private final ProductRepository productRepository;
    private final PricingPolicyRepository pricingPolicyRepository;


    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);


    public Calculation handle(CalculateDiscountCommand command) {
        var product = getProduct(command.getProductId());
        var policies = pricingPolicyRepository.findAllByIds(command.policiesIds);
        validatePolicies(policies);

        var productWithTaxPrice = product.price().multiply(product.vatPercent().divide(HUNDRED, 2, RoundingMode.HALF_UP));
        var discountedProductPrice =

        return Calculation
                .builder()
                .productCount(command.productCount)
                .productName(product.name())
                .productId(command.productId)
                .productBasePrice(product.price())
                .productBasePriceWithTax(productWithTaxPrice)
                .build();

    }

    private void validatePolicies(List<PricingPolicyAbstract> policies) {
        if (policies.size() > 1 && policies.stream().anyMatch(it -> !it.getCanBeAppliedWithOtherPolicies())) {
            throw new IllegalStateException("All policies must be applicable with other policies");
        }
    }

    private BigDecimal calculateDiscountOnProduct(@NotNull BigDecimal price, @NotNull PricingPolicyAbstract policy){


    }

    private Product getProduct(@NotNull UUID productId) {
        return productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Could not find product with id: " + productId));
    }


    @Builder
    @Data
    public static class CalculateDiscountCommand {

        @NotNull UUID productId;
        @NotNull List<UUID> policiesIds;
        @NotNull Integer productCount;
    }
}
