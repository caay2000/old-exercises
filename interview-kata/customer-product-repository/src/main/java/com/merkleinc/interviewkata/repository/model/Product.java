package com.merkleinc.interviewkata.repository.model;

import java.util.StringJoiner;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    private final String id;
    private final String name;
    private final String price;

    @JsonCreator
    public Product(@JsonProperty("id") String id,
                   @JsonProperty("name") String name,
                   @JsonProperty("price") String price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Product.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("name='" + name + "'")
                .add("price='" + price + "'")
                .toString();
    }
}
