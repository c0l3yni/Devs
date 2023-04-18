package com.tekgs.nextgen.tekegg.data.cart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tekgs.nextgen.tekegg.data.cart.item.Item;
import com.tekgs.nextgen.tekegg.data.cart.item.ItemCalibratable;
import com.tekgs.nextgen.tekegg.data.financial.payment.TekEggPayment;

import java.util.ArrayList;
import java.util.List;

public class Cart implements CartCalibratable {
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final List<Item> items = new ArrayList<>();
    private final String id;

    public Cart() {
        this(null);
    }

    public Cart(CartCalibratable cartDefinition) {
        this.id = cartDefinition != null && cartDefinition.getId() != null ? cartDefinition.getId() : "-1";
    }

    public static Cart getInstance() {
        return new Cart(null);
    }

    public static Cart getInstance(CartCalibratable cartDefinition) {
        return new Cart(cartDefinition);
    }

    private static boolean areEquivalent(Object comparatorValue, Object thisValue) {
        return comparatorValue == null || thisValue.equals(comparatorValue);
    }

    public String getId() {
        return this.id;
    }

    @Override
    public Integer getTotal() {
        return this.items.stream().reduce(0, (subTotal, item) -> subTotal + item.getProduct().getPrice() * item.getQuantity(), Integer::sum);
    }

    @Override
    public List<ItemCalibratable> getItems() {
        return new ArrayList<>(this.items);
    }

    @Override
    public Boolean isPurchasable() {
        Integer total = getTotal();
        return total >= TekEggPayment.VALID_AMOUNT_MIN && total <= TekEggPayment.VALID_AMOUNT_MAX;
    }
    
    @Override
    public Boolean isCartEmpty() {
        return this.items.isEmpty();
    }
    
    @Override
    public boolean equivalent(CartCalibratable comparator) {
        if (comparator == null) {
            return false;
        }
        if (comparator.getId() != null && this.getId().equals(comparator.getId())) {
            return true;
        }
        if (comparator.isCartEmpty() && this.isCartEmpty()) {
            return true;
        }
        boolean isEquivalent = areEquivalent(comparator.getTotal(), this.getTotal());
        isEquivalent &= itemsAreEquivalent(comparator.getItems());
        isEquivalent &= areEquivalent(comparator.isPurchasable(), this.isPurchasable());
        return isEquivalent;
    }

    private boolean itemsAreEquivalent(List<ItemCalibratable> comparatorItems) {
        List<ItemCalibratable> thisItems = new ArrayList<>(items);
        for (ItemCalibratable comparatorItem : comparatorItems) {
            ItemCalibratable itemFound = null;
            for (ItemCalibratable candidate : thisItems) {
                if (candidate.equivalent(comparatorItem)) {
                    itemFound = candidate;
                    break;
                }
            }
            if (itemFound == null) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(this);
    }

    public ItemCalibratable getAnyItem() {
        return items.size() == 0 ? null : items.get(0);
    }
}
