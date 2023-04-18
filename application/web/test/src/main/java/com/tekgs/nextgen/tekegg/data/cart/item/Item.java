package com.tekgs.nextgen.tekegg.data.cart.item;

import com.tekgs.nextgen.tekegg.data.product.Product;
import com.tekgs.nextgen.tekegg.data.product.ProductCalibratable;

public class Item implements ItemCalibratable {
    private final Integer quantity;
    private final Product product;

    public Item() {
        quantity = null;
        product = null;
    }

    private static boolean areEquivalent(Object comparatorValue, Object thisValue) {
        return comparatorValue == null || thisValue.equals(comparatorValue);
    }

    @Override
    public ProductCalibratable getProduct() {
        return product;
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
        boolean isEquivalent = comparator.getProduct() == null || (this.product != null && this.product.equivalent(comparator.getProduct()));
        isEquivalent &= areEquivalent(comparator.getQuantity(), this.getQuantity());
        return isEquivalent;
    }

    @Override
    public void increaseQuantity() {
    }

    @Override
    public void decreaseQuantity() {
    }
}
