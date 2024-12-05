package org.example.inpost.domain;

import org.jetbrains.annotations.NotNull;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {

   Optional<Product> findById(@NotNull UUID id);
}
