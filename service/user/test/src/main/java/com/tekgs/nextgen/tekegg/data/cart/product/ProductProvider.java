package com.tekgs.nextgen.tekegg.data.cart.product;

import com.tekgs.nextgen.tekegg.data.cart.CartProvider;

public class ProductProvider {

    public static ProductProvider getInstance() {
        return new ProductProvider();
    }

    public Product get(String productId) {
        ProductResponse productResponse = ProductRequest.getInstance(productId).getById();
        return productResponse.getProduct();
    }
}
