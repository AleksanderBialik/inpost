package org.example.inpost.infrastructure.db;

import org.example.inpost.domain.PricingPolicyAbstract;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.UUID;

public interface MongoPricingPolicyRepository extends MongoRepository<PricingPolicyAbstract, UUID> {

    List<PricingPolicyAbstract> findAllByIdIsIn(@NotNull List<UUID> ids);
}
