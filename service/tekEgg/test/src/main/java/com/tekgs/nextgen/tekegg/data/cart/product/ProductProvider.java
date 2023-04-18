package com.tekgs.nextgen.tekegg.data.cart.product;

public class ProductProvider {

    public static ProductProvider getInstance() {
        return new ProductProvider();
    }

    public Product get(String productId) {
        ProductResponse productResponse = ProductRequest.getInstance(productId).getById();
        return productResponse.getProduct();
    }
}
