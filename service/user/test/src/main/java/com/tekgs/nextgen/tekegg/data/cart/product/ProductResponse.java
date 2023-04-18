package com.tekgs.nextgen.tekegg.data.cart.product;

import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;

import java.util.List;

public class ProductResponse {
    private final List<Product> products;

    public ProductResponse(Response response) {
        this.products = response.readEntity(new GenericType<>() {});
    }

    public static ProductResponse getInstance(Response response) {
        return new ProductResponse(response);
    }

    public Product getProduct() {
        return products.get(0);
    }
}
