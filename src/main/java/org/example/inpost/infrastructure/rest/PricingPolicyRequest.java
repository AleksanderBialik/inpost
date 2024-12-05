package org.example.inpost.infrastructure.rest;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import org.example.inpost.common.PolicyType;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = ProductAmountBasedPricingPolicyRequest.class, name = "PRODUCT_AMOUNT"),
})
public interface PricingPolicyRequest {
    PolicyType type();
}
