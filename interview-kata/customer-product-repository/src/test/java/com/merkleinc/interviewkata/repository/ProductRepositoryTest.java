package com.merkleinc.interviewkata.repository;

import java.io.IOException;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import com.merkleinc.interviewkata.repository.model.Product;

public class ProductRepositoryTest {

    @Test
    public void setUp() throws IOException {

        ProductRepository testee = new ProductRepository();

        List<Product> products = testee.getProducts();

        Assert.assertNotNull(products);
    }
}