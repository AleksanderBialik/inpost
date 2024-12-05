package org.example.inpost

import org.example.inpost.domain.Product

class ProductFixture {

    static Product product(Map args) {
        def defaults = [
                id        : UUID.randomUUID(),
                name      : "test",
                price     : BigDecimal.ONE,
                vatPercent: BigDecimal.valueOf(23),

        ]
        args = defaults + args
        return Product.builder()
                .id((args.id as UUID) ?: UUID.randomUUID())
                .name((args.name as String) ?: "test")
                .price((args.price as BigDecimal) ?: BigDecimal.ONE)
                .vatPercent((args.vatPercent) as BigDecimal ?: BigDecimal.valueOf(23))
                .build()
    }
}
