package com.merkleinc.interviewkata.repository.model;

import java.util.StringJoiner;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
public class CustomerProduct {

    String id;
    String customerId;
    String productId;
    String effectiveFrom;
    String effectiveTo;

    @JsonCreator
    public CustomerProduct(
            @JsonProperty("id") String id,
            @JsonProperty("customerId") String customerId,
            @JsonProperty("productId") String productId,
            @JsonProperty("effectiveFrom") String effectiveFrom,
            @JsonProperty("effectiveTo") String effectiveTo) {
        this.id = id;
        this.customerId = customerId;
        this.productId = productId;
        this.effectiveFrom = effectiveFrom;
        this.effectiveTo = effectiveTo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(String effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public String getEffectiveTo() {
        return effectiveTo;
    }

    public void setEffectiveTo(String effectiveTo) {
        this.effectiveTo = effectiveTo;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CustomerProduct.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("customerId='" + customerId + "'")
                .add("productId='" + productId + "'")
                .add("effectiveFrom='" + effectiveFrom + "'")
                .add("effectiveTo='" + effectiveTo + "'")
                .toString();
    }
}
