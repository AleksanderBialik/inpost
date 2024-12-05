package org.example.inpost.infrastructure.db;

import lombok.RequiredArgsConstructor;
import org.example.inpost.domain.PricingPolicyAbstract;
import org.example.inpost.domain.PricingPolicyRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DbPricingPolicyRepository implements PricingPolicyRepository {

    private final MongoPricingPolicyRepository repository;

    public void save(@NotNull PricingPolicyAbstract pricingPolicy) {
        repository.save(pricingPolicy);
    }

    @Override
    public List<PricingPolicyAbstract> findAllByIds(@NotNull List<UUID> ids) {
        return repository.findAllByIdIsIn(ids);
    }
}
