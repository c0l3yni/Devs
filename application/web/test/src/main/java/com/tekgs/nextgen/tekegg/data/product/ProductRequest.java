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
    
    public ProductRequest() {
        this.client = ClientBuilder.newClient();
    }
    
    public static ProductRequest getInstance() {
        return new ProductRequest();
    }
    
    public ProductResponse getAll() {
        ProductResponse productResponse;
        WebTarget target = client.target(INVENTORY_SERVICE_URI);
        try (Response response = target.request().get()) {
            productResponse = ProductResponse.getInstance(response);
        } finally {
            client.close();
        }
        return productResponse;
    }
}
