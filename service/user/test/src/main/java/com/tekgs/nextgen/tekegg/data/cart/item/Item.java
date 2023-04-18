package com.tekgs.nextgen.tekegg.data.cart.item;

import com.tekgs.nextgen.tekegg.data.cart.product.Product;
import com.tekgs.nextgen.tekegg.data.cart.product.ProductCalibratable;
import com.tekgs.nextgen.tekegg.data.cart.product.ProductProvider;

public class Item implements ItemCalibratable {
    private final Integer quantity;
    private final Product product;
    private final String productId;

    public Item() {
        this.quantity = null;
        this.product = null;
        this.productId = null;
    }

    @Override
    public ProductCalibratable getProduct() {
        return product == null ? ProductProvider.getInstance().get(this.productId): this.product;
    }

    @Override
    public Integer getQuantity() {
        return quantity;
    }

    @Override
    public boolean equivalent(ItemCalibratable comparator) {
        if (comparator == null) {
            return false;
        }
        if (comparator.getProduct().getId() != null && this.getProduct().getId().equals(comparator.getProduct().getId())) {
            return true;
        }
        @SuppressWarnings("ConstantConditions")
        boolean isEquivalent = this.product.equivalent(comparator.getProduct());
        isEquivalent &= comparator.getQuantity() == null || this.getQuantity().equals(comparator.getQuantity());
        return isEquivalent;
    }
    
}
