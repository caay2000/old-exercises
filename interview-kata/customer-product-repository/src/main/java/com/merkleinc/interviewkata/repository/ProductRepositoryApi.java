package com.merkleinc.interviewkata.repository;

import java.io.IOException;
import java.util.List;
import com.merkleinc.interviewkata.repository.model.Product;

public interface ProductRepositoryApi {

    List<Product> getProducts() throws IOException;
}
