package com.tekgs.nextgen.tekegg.region.product;

import com.tekgs.nextgen.tekegg.data.product.ProductCalibratable;

public class ProductRegionExpected implements ProductRegionCalibratable {
    private final ProductCalibratable product;
    private final boolean isOutOfStock;
    private final boolean  isProductInCart;

    public ProductRegionExpected(ProductCalibratable product, boolean isProductInCart) {
        this.product = product;
        this.isOutOfStock = this.product.getStock() <= 0;
        this.isProductInCart = isProductInCart;
    }

    public static ProductRegionExpected getInstance(ProductCalibratable product, boolean isProductInCart) {
        return new ProductRegionExpected(product, isProductInCart);
    }

    @Override
    public boolean equivalent(ProductRegionCalibratable comparator) {
        return false;
    }

    @Override
    public String getProductDescription() {
        String hiddenBackspaceCharacter = "(U+2408)";
        String description = this.product.getDescription();
        return description == null || description.contains(hiddenBackspaceCharacter) ? "" : description;
    }

    @Override
    public String getOutOfStockMessage() {
        return isOutOfStock ? "Out of Stock" : null;
    }

    @Override
    public String getButtonDisabledStatus() {
        return isOutOfStock || isProductInCart ? "true" : null;
    }


}
