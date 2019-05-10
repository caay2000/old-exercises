package com.merkleinc.interviewkata.repository;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.merkleinc.interviewkata.repository.model.Product;
public class ProductRepository implements ProductRepositoryApi {

    private final List<Product> products;

    public ProductRepository() throws IOException {
        this.products = Collections.unmodifiableList(
                new ObjectMapper().readValue(
                        new InputStreamReader(ProductRepository.class.
                                getResourceAsStream("/product/products.json")),
                        new TypeReference<List<Product>>() {
                        }));
    }

    @Override
    public List<Product> getProducts() {
        return products;
    }
}
