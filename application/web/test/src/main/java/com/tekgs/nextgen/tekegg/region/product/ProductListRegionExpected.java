package com.tekgs.nextgen.tekegg.region.product;

import com.tekgs.nextgen.tekegg.data.cart.Cart;
import com.tekgs.nextgen.tekegg.data.product.ProductCalibratable;

import java.util.ArrayList;
import java.util.List;

public class ProductListRegionExpected implements ProductListRegionCalibratable {
    private final List<ProductCalibratable> productList = new ArrayList<>();
    private final Cart cart;


    public ProductListRegionExpected(List<ProductCalibratable> productList, Cart cart) {
        this.productList.addAll(productList);
        this.cart = cart;
    }

    public static ProductListRegionExpected getInstance(List<ProductCalibratable> productList, Cart cart) {
        return new ProductListRegionExpected(productList, cart);
    }

    @Override
    public List<ProductRegionCalibratable> getProducts() {
        List<ProductRegionCalibratable> productRegions = new ArrayList<>();
        this.productList.forEach(product -> {
            boolean isProductInCart = false;
            if (this.cart != null) {
                isProductInCart = this.cart.getItems().stream().anyMatch(item -> item.getProduct().getId().equals(product.getId()));
            }
            productRegions.add(ProductRegionExpected.getInstance(product, isProductInCart));
        });
        return productRegions;
    }


}
