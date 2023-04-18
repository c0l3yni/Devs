package com.tekgs.nextgen.tekegg.view.products;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tekgs.nextgen.tekegg.data.cart.Cart;
import com.tekgs.nextgen.tekegg.data.product.Product;
import com.tekgs.nextgen.tekegg.data.product.ProductCalibratable;
import com.tekgs.nextgen.tekegg.data.product.ProductProvider;
import com.tekgs.nextgen.tekegg.region.product.ProductListRegionExpected;

import java.util.ArrayList;
import java.util.List;

public class ProductsViewExpected implements ProductsViewCalibratable {
    private final List<ProductCalibratable> productList = new ArrayList<>();
    private final Cart cart;
    
    public ProductsViewExpected(Cart cart) {
        this.productList.addAll(ProductProvider.getInstance().get());
        this.cart = cart;
    }
    
    public static ProductsViewExpected getInstance() {
        return new ProductsViewExpected(null);
    }

    public static ProductsViewExpected getInstance(Cart cart) {
        return new ProductsViewExpected(cart);
    }

    @Override
    public ProductListRegionExpected inProducts() {
        return ProductListRegionExpected.getInstance(productList, cart);
    }
    
    @Override
    public String getEmptyMessage() {
        return productList.isEmpty() ? "No products available" : null;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(this);
    }
}
