package com.tekgs.nextgen.tekegg.data.product;


import java.util.List;

public class ProductProvider {
    public static ProductProvider getInstance() {
        return new ProductProvider();
    }

    public List<Product> get() {
        return ProductRepository.getInstance().query();
    }

    public Product get(ProductDefinition productDefinition) {
        List<Product> productList = ProductRepository.getInstance().query();
        Product product = null;
        for (Product candidate : productList) {
            if (candidate.equivalent(productDefinition)) {
                product = candidate;
                break;
            }
        }
        return product;
    }
}
