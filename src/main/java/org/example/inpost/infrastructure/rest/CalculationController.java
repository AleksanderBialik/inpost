package org.example.inpost.infrastructure.rest;

import lombok.RequiredArgsConstructor;
import org.example.inpost.application.DiscountCalculationHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/calculation")
public class CalculationController {

    private final DiscountCalculationHandler discountCalculationHandler;

    @GetMapping()
    public ResponseEntity<CalculationResponse> getProductCalculation(@RequestParam UUID productId, @RequestParam List<UUID> policiesIds, @RequestParam Integer productsCount) {
        var command = DiscountCalculationHandler.CalculateDiscountCommand.builder().productId(productId).policiesIds(policiesIds).productCount(productsCount).build();
        var calculation = discountCalculationHandler.handle(command);
        var response = CalculationResponse.builder()
                .productId(calculation.productId())
                .productName(calculation.productName())
                .productQuantity(calculation.productQuantity())
                .productBasePrice(calculation.productBasePrice())
                .totalDiscountedPrice(calculation.totalDiscountedPrice())
                .totalDiscountedPriceWithTax(calculation.totalDiscountedPriceWithTax())
                .build();
        return ResponseEntity.ok().body(response);

    }
}
