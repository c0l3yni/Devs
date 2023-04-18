package com.tekgs.nextgen.tekegg.data.cart;

import com.tekgs.nextgen.tekegg.data.cart.item.ItemCalibratable;
import com.tekgs.nextgen.tekegg.data.cart.item.ItemExpected;
import com.tekgs.nextgen.tekegg.data.financial.payment.TekEggPaymentDefinition;
import com.tekgs.nextgen.tekegg.data.product.ProductCalibratable;

import java.util.ArrayList;
import java.util.List;

public class CartExpected implements CartCalibratable {
    private final CartCalibratable cart;
    private final List<ItemCalibratable> items = new ArrayList<>();
    
    private CartExpected(CartCalibratable cart, ProductCalibratable product, Integer quantity) {
        this.cart = cart;
        List<ItemCalibratable> updatedItemList = cart == null ? new ArrayList<>() : cart.getItems();
        if (product != null) {
            ItemCalibratable itemToUpdate = updatedItemList.stream()
                    .filter(candidate -> candidate.getProduct().equivalent(product))
                    .findFirst()
                    .orElse(null);
            int indexOfCandidate = updatedItemList.indexOf(itemToUpdate);
            updatedItemList.set(indexOfCandidate, ItemExpected.getInstance(product, quantity));
        }
        items.addAll(updatedItemList);
    }
    
    public static CartExpected getInstance(CartCalibratable cart) {
        return new CartExpected(cart, null, null);
    }
    
    public static CartExpected getInstance(CartCalibratable cart, ProductCalibratable product, Integer quantity) {
        return new CartExpected(cart, product, quantity);
    }
    
    @Override
    public Integer getTotal() {
        return this.items.stream().reduce(0, (subTotal, item) -> subTotal + item.getProduct().getPrice() * item.getQuantity(), Integer::sum);
    }
    
    @Override
    public String getId() {
        return cart.getId();
    }
    
    @Override
    public List<ItemCalibratable> getItems() {
        return items;
    }
    
    @Override
    public Boolean isPurchasable() {
        Integer total = getTotal();
        return total >= TekEggPaymentDefinition.VALID_AMOUNT_MIN && total <= TekEggPaymentDefinition.VALID_AMOUNT_MAX;
    }
    
    @Override
    public Boolean isCartEmpty() {
        return this.items.isEmpty();
    }
    
    @Override
    public CartCalibratable toPayload() {
        return null;
    }
    
    @Override
    public boolean equivalent(CartCalibratable comparator) {
        return false;
    }
}
