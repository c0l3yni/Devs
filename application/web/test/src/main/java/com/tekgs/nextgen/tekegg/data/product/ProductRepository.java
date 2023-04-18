package com.tekgs.nextgen.tekegg.data.product;

import java.util.List;

public class ProductRepository {
    public static ProductRepository getInstance() {
        return new ProductRepository();
    }
    
    public List<Product> queryAll() {
        return this.getProductsFromService();
    }
    
    private List<Product> getProductsFromService() {
        return ProductRequest.getInstance().getAll().getProductList();
    }
    
}
