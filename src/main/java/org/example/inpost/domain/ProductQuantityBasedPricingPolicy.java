package org.example.inpost.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;
import org.example.inpost.common.DiscountLevel;
import org.example.inpost.common.PolicyCalculationType;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@Slf4j
public class ProductQuantityBasedPricingPolicy extends PricingPolicyAbstract {

    @NotNull List<DiscountLevel> discountLevels;

    private static final BigDecimal HUNDRED = BigDecimal.valueOf(100);


    @Override
    public BigDecimal calculateDiscount(@NotNull BigDecimal currentPrice, @NotNull Integer productQuantity, @NotNull Product product) {
        if (this.getMinProductsPriceBeforeTax().compareTo(product.price().multiply(BigDecimal.valueOf(productQuantity))) > 0) {
            log.info("Skipping discount calculation of {} to the products {} as the minimum price of the products is too small", this.name, product.name());
            return BigDecimal.ZERO;
        }
        var level = discountLevels.stream().filter(it -> productQuantity >= it.minProductsQuantity()).max(Comparator.comparingInt(DiscountLevel::minProductsQuantity));
        if (level.isEmpty()) {
            log.info("Skipping discount calculation of the discount {} to the products {} as the discount level could not be matched", this.name, product.name());
            return BigDecimal.ZERO;
        }
        return switch (this.calculationType) {
            case FLAT -> level.get().value();
            case PERCENTAGE -> currentPrice.multiply(level.get().value().divide(HUNDRED));
        };

    }
}
