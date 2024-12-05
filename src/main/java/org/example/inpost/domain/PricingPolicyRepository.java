package org.example.inpost.domain;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;

public interface PricingPolicyRepository {

    void save(@NotNull PricingPolicyAbstract pricingPolicy);

    List<PricingPolicyAbstract> findAllByIds(@NotNull List<UUID> ids);
}
