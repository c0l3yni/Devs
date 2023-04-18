package com.tekgs.nextgen.tekegg.data.cart;


import com.google.gson.Gson;
import com.tekgs.nextgen.tekegg.data.cart.item.ItemCalibratable;
import com.tekgs.nextgen.tekegg.data.cart.item.ItemDefinition;
import com.tekgs.nextgen.tekegg.data.financial.payment.TekEggPayment;
import com.tekgs.nextgen.tekegg.data.product.ProductDefinition;

import java.util.ArrayList;
import java.util.List;

public class CartDefinition implements CartCalibratable {
    private final List<ItemCalibratable> items = new ArrayList<>();
    private Integer total;
    private String id;
    private Boolean isPurchasable = true;
    private Boolean isCartEmpty = false;

    public static CartDefinition getInstance() {
        return new CartDefinition();
    }

    public CartDefinition withTotal(Integer total) {
        this.total = total;
        return this;
    }

    @Override
    public Integer getTotal() {
        return this.total;
    }

    @Override
    public List<ItemCalibratable> getItems() {
        return new ArrayList<>(this.items);
    }

    @Override
    public Boolean isPurchasable() {
        Integer total = getTotal();
        return total == null
                ? isPurchasable
                : total >= TekEggPayment.VALID_AMOUNT_MIN && total <= TekEggPayment.VALID_AMOUNT_MAX;
    }
    
    @Override
    public Boolean isCartEmpty() {
        return this.isCartEmpty;
    }
    
    @Override
    public boolean equivalent(CartCalibratable comparator) {
        return false;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return String.format("%s %s ", this.getClass().getSimpleName(), new Gson().toJson(this));
    }

    public CartDefinition withItem(ItemDefinition itemDefinition) {
        this.items.add(itemDefinition);
        return this;
    }

    public CartDefinition withProduct(ProductDefinition productDefinition) {
        this.items.add(ItemDefinition.getInstance().withProduct(productDefinition));
        return this;
    }

    public CartDefinition withPurchasableAmount(boolean isPurchasable) {
        this.isPurchasable = isPurchasable;
        return this;
    }

    public CartDefinition withNoItems() {
        this.isCartEmpty = true;
        return this;
    }

    public CartDefinition withItems(List<ItemDefinition> itemDefinitionList) {
        this.items.addAll(itemDefinitionList);
        return this;
    }
}
