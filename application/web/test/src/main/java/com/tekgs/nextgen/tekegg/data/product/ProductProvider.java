package com.tekgs.nextgen.tekegg.data.product;


import java.util.List;

public class ProductProvider {
    public static ProductProvider getInstance() {
        return new ProductProvider();
    }

    public List<Product> get() {
        return ProductRepository.getInstance().queryAll();
    }
}
