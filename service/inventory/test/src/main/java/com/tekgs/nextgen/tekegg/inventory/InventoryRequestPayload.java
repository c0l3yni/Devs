package com.tekgs.nextgen.tekegg.inventory;

public class InventoryRequestPayload {
    private final String productId;

    private InventoryRequestPayload(String productId) {
        this.productId = productId;
    }

    public static InventoryRequestPayload getInstance(String productId) {
        return new InventoryRequestPayload(productId);
    }

    public String getProductId() {
        return productId;
    }
}
