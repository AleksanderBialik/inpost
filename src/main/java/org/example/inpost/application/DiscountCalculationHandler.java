package org.example.inpost.application;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.example.inpost.common.NotFoundException;
import org.example.inpost.common.PolicyCalculationType;
import org.example.inpost.domain.Calculation;
import org.example.inpost.domain.PricingPolicyAbstract;
import org.example.inpost.domain.PricingPolicyRepository;
import org.example.inpost.domain.Product;
import org.example.inpost.domain.ProductRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
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


        var productsPrice = product.price().multiply(BigDecimal.valueOf(command.productQuantity));
        var productsWithTaxPrice = product.price().multiply(product.vatPercent().divide(HUNDRED, 2, RoundingMode.HALF_UP).add(BigDecimal.ONE)).multiply(BigDecimal.valueOf(command.productQuantity));
        var discountedPrice = calculateDiscountedPriceWithPercentagePriority(product, command.productQuantity, productsPrice, policies);
        var discountedTaxedPrice = calculateDiscountedPriceWithPercentagePriority(product, command.productQuantity, productsWithTaxPrice, policies);

        return Calculation
                .builder()
                .productQuantity(command.productQuantity)
                .productName(product.name())
                .productId(command.productId)
                .productBasePrice(product.price())
                .totalDiscountedPrice(discountedPrice.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : discountedPrice.setScale(2, RoundingMode.HALF_UP))
                .totalDiscountedPriceWithTax(discountedTaxedPrice.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : discountedTaxedPrice.setScale(2, RoundingMode.HALF_UP))
                .build();

    }

    private BigDecimal calculateDiscountedPriceWithPercentagePriority(@NotNull Product product, @NotNull Integer productQuantity, @NotNull BigDecimal currentPrice, @NotNull List<PricingPolicyAbstract> policies) {
        var price = currentPrice;
        var sortedPolicies = policies.stream()
                .sorted(Comparator.comparingInt(item -> item.getCalculationType() == PolicyCalculationType.PERCENTAGE ? 0 : 1))
                .toList();

        for (PricingPolicyAbstract policy : sortedPolicies) {
            var discountValue = policy.calculateDiscount(price, productQuantity, product);
            price = price.subtract(discountValue);
        }
        return price;
    }

    private void validatePolicies(List<PricingPolicyAbstract> policies) {
        if (policies.size() > 1 && policies.stream().anyMatch(it -> !it.getCanBeAppliedWithOtherPolicies())) {
            throw new IllegalStateException("All policies must be applicable with other policies");
        }
    }


    private Product getProduct(@NotNull UUID productId) {
        return productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Could not find product with id: " + productId));
    }


    @Builder
    @Data
    public static class CalculateDiscountCommand {

        @NotNull UUID productId;
        @NotNull List<UUID> policiesIds;
        @NotNull Integer productQuantity;
    }
}
