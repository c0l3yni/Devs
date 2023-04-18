package com.tekgs.nextgen.tekegg.inventory;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

public class InventoryRequest {
    private static final String DOMAIN_URL = "http://localhost:8001";
    private static final String ENDPOINT = "inventory";
    private static final String INVENTORY_SERVICE_URI = String.format("%s/%s", DOMAIN_URL, ENDPOINT);
    private final Client client;
    private final InventoryRequestPayload inventoryRequestPayload;

    private InventoryRequest(String productId) {
        this.client = ClientBuilder.newClient();
        this.inventoryRequestPayload = InventoryRequestPayload.getInstance(productId);
    }

    public static InventoryRequest getInstance() {
        return new InventoryRequest(null);
    }
    public static InventoryRequest getInstance(String productId) {
        return new InventoryRequest(productId);
    }


    private InventoryResponse get(WebTarget target) {
        InventoryResponse inventoryResponse;
        try(Response response = target.request().get()){
            inventoryResponse = InventoryResponse.getInstance(response);
        } finally {
            client.close();
        }
        return inventoryResponse;
    }

    public InventoryResponse getAll() {
        WebTarget target = this.client.target(INVENTORY_SERVICE_URI);
        return this.get(target);
    }


    public InventoryResponse getById() {
        String getUrl = String.format("%s/%s",INVENTORY_SERVICE_URI,this.inventoryRequestPayload.getProductId());
        WebTarget target = this.client.target(getUrl);
        return this.get(target);
    }
}
