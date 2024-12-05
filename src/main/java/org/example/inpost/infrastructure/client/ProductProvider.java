package org.example.inpost.infrastructure.client;

import jakarta.annotation.PostConstruct;
import org.example.inpost.domain.Product;
import org.example.inpost.domain.ProductRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductProvider implements ProductRepository {

    private final Map<UUID, Product> products = new HashMap<>();

    @PostConstruct
    public void init() {
        products.put(UUID.fromString("00000000-0000-0000-0000-000000000001"), Product.builder().id(UUID.fromString("00000000-0000-0000-0000-000000000001")).name("Laptop").price(BigDecimal.valueOf(100)).vatPercent(BigDecimal.valueOf(23)).build());
        products.put(UUID.fromString("00000000-0000-0000-0000-000000000002"), Product.builder().id(UUID.fromString("00000000-0000-0000-0000-000000000002")).name("Phone").price(BigDecimal.valueOf(333)).vatPercent(BigDecimal.valueOf(8)).build());
        products.put(UUID.fromString("00000000-0000-0000-0000-000000000003"), Product.builder().id(UUID.fromString("00000000-0000-0000-0000-000000000003")).name("Headphones").price(BigDecimal.valueOf(99)).vatPercent(BigDecimal.valueOf(5)).build());
    }

    @Override
    public Optional<Product> findById(@NotNull UUID id) {
        return Optional.ofNullable(products.get(id));
    }
}
