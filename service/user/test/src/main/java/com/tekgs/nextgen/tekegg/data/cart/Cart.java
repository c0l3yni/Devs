package com.tekgs.nextgen.tekegg.data.cart;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tekgs.nextgen.tekegg.data.cart.item.Item;
import com.tekgs.nextgen.tekegg.data.cart.item.ItemCalibratable;

import java.util.ArrayList;
import java.util.List;

public class Cart implements CartCalibratable {
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final List<Item> items = new ArrayList<>();
    private final String id;
    private final Long updatedAt;

    @SuppressWarnings("unused")
    private Cart() {
        this(null);
    }

    private Cart(CartCalibratable cartDefinition) {
        this.id = cartDefinition != null && cartDefinition.getId() != null ? cartDefinition.getId() : String.valueOf(-1);
        this.updatedAt = null;
    }

    public static Cart getInstance() {
        return new Cart(null);
    }

    public static Cart getInstance(CartCalibratable cartDefinition) {
        return new Cart(cartDefinition);
    }

    public String getId() {
        return this.id;
    }

    @Override
    public List<ItemCalibratable> getItems() {
        return new ArrayList<>(this.items);
    }

    public Long getUpdatedAt(){
        return updatedAt;
    }
    @Override
    public boolean equivalent(CartCalibratable comparator) {
        if (comparator == null) {
            return false;
        }
        if (comparator.getId() != null && this.getId().equals(comparator.getId())) {
            return true;
        }
        return itemsAreEquivalent(comparator.getItems());
    }

    private boolean itemsAreEquivalent(List<ItemCalibratable> comparatorItems) {
        List<ItemCalibratable> equivalentItems = comparatorItems.stream()
                .filter(actualItem -> this.getItems().stream()
                        .anyMatch(candidate -> candidate.equivalent(actualItem)))
                .toList();
        return equivalentItems.size() == comparatorItems.size();
    }

    @Override
    public String toString() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        return gson.toJson(this);
    }
}
