package com.tekgs.nextgen.tekegg.data.product;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

public class ProductRequest {
    private static final String DOMAIN_URL = "http://localhost:8001";
    private static final String ENDPOINT = "inventory";
    private static final String INVENTORY_SERVICE_URI = String.format("%s/%s", DOMAIN_URL, ENDPOINT);
    Client client;
    private final String id;
    
    public ProductRequest(String id) {
        this.client = ClientBuilder.newClient();
        this.id = id;
    }
    
    public static ProductRequest getInstance(String id) {
        return new ProductRequest(id);
    }
    
    public ProductResponse get() {
        ProductResponse productResponse;
        WebTarget target = client.target(String.format("%s/%s", INVENTORY_SERVICE_URI, id));
        try (Response response = target.request().get()) {
            productResponse = ProductResponse.getInstance(response);
        } finally {
            client.close();
        }
        return productResponse;
    }
}
