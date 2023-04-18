package com.tekgs.nextgen.tekegg.data.cart.product;

import com.tekgs.nextgen.tekegg.user.cart.CartRequest;
import com.tekgs.nextgen.tekegg.user.cart.CartResponse;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;

public class ProductRequest {
    private static final String DOMAIN_URL = "http://localhost:8000";
    private static final String ENDPOINT = "inventory";
    private static final String INVENTORY_SERVICE_URI = String.format("%s/%s", DOMAIN_URL, ENDPOINT);
    private final String productId;
    private final Client client;
    public ProductRequest(String productId) {
        this.productId = productId;
        client = ClientBuilder.newClient();
    }

    public static ProductRequest getInstance(String productId) {
        return new ProductRequest(productId);
    }

    public ProductResponse getById() {
        WebTarget target = this.client.target(INVENTORY_SERVICE_URI);
        ProductResponse productResponse;
        try (Response response = target.request().get()) {
            productResponse = ProductResponse.getInstance(response);
        } finally {
            client.close();
        }
        return productResponse;
    }
}
