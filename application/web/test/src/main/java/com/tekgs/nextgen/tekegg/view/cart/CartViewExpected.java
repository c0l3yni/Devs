package com.tekgs.nextgen.tekegg.view.cart;

import com.tekgs.nextgen.tekegg.data.cart.Cart;
import com.tekgs.nextgen.tekegg.data.cart.CartCalibratable;
import com.tekgs.nextgen.tekegg.data.cart.item.ItemCalibratable;
import com.tekgs.nextgen.tekegg.data.product.ProductCalibratable;
import com.tekgs.nextgen.tekegg.data.value.Cents;
import com.tekgs.nextgen.tekegg.view.cart.item.ItemListRegionExpected;

import java.util.ArrayList;
import java.util.List;

public class CartViewExpected implements CartViewCalibratable {
    public static final int MAXIMUM_TOTAL = 900_000_00;
    public static final int MINIMUM_TOTAL = 50;
    private final CartCalibratable cart;
    private final List<ItemCalibratable> items = new ArrayList<>();

    private CartViewExpected(CartCalibratable cart, ProductCalibratable product, Integer newQuantity) {
        this.cart = cart;
        List<ItemCalibratable> updatedItemList = cart == null ? new ArrayList<>() : cart.getItems();
        if (product != null) {
            updateItemQuantity(updatedItemList, product, newQuantity);
        }
        items.addAll(updatedItemList);
    }

    public static CartViewExpected getInstance() {
        return new CartViewExpected(null, null, null);
    }

    public static CartViewExpected getInstance(CartCalibratable cart) {
        return new CartViewExpected(cart, null, null);
    }

    public static CartViewExpected getInstance(Cart cart, ProductCalibratable product, int newQuantity) {
        return new CartViewExpected(cart, product, newQuantity);
    }

    private void updateItemQuantity(List<ItemCalibratable> updatedItemList, ProductCalibratable product, Integer newQuantity) {
        ItemCalibratable itemToUpdate = updatedItemList.stream()
                .filter(candidate -> candidate.getProduct().equivalent(product))
                .findFirst()
                .orElse(null);
        if (itemToUpdate == null) {
            return;
        }
        int quantityDifference = newQuantity - itemToUpdate.getQuantity();
        while (quantityDifference != 0) {
            if (quantityDifference > 0) {
                itemToUpdate.increaseQuantity();
                quantityDifference -= 1;
            } else {
                itemToUpdate.decreaseQuantity();
                quantityDifference += 1;
            }
        }
    }

    @Override
    public String getTotalAmount() {
        return Cents.getInstance(cart == null ? 0 : cart.getTotal()).inDollarFormat();
    }

    @Override
    public ItemListRegionExpected inItems() {
        return ItemListRegionExpected.getInstance(items);
    }

    @Override
    public String getEmptyMessage() {
        return this.cart == null || items.isEmpty() ? "Your cart is empty" : null;
    }

    @Override
    public String getMaximumTotalMessage() {
        return this.cart != null && this.cart.getTotal() > MAXIMUM_TOTAL ? "Your cart total needs to be less than $900,000.00 to checkout" : null;
    }
    @Override
    public String getMinimumTotalMessage() {
        return this.cart == null || this.cart.getTotal() < MINIMUM_TOTAL ? "Your cart total needs to be more than 50 cents to checkout" : null;
    }

    @Override
    public Boolean isButtonEnabled() {
        return this.cart != null && (this.cart.getTotal() >= MINIMUM_TOTAL && this.cart.getTotal() <= MAXIMUM_TOTAL);
    }

}
