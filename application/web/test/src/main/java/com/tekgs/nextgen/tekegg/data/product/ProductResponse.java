package com.tekgs.nextgen.tekegg.data.product;

import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;

import java.util.List;

public class ProductResponse {
    private final List<Product> productsList;
    
    public ProductResponse(Response response) {
        this.productsList = response.readEntity(new GenericType<>(){});
    }
    
    public static ProductResponse getInstance(Response response) {
        return new ProductResponse(response);
    }
    
    public List<Product> getProductList() {
        return this.productsList;
    }
}
