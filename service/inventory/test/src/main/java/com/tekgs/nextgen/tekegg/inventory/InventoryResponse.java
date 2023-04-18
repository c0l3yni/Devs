package com.tekgs.nextgen.tekegg.inventory;

import com.tekgs.nextgen.tekegg.data.product.Product;
import com.tekgs.nextgen.tekegg.data.product.ProductCalibratable;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

public class InventoryResponse implements InventoryResponseCalibratable {
    public static final String successMessage = "OK";
    private final List<Product> products;
    private final Response.StatusType statusInfo;
    
    public InventoryResponse(Response response) {
        this.products = response.readEntity(new GenericType<>() {
        });
        this.statusInfo = response.getStatusInfo();
    }
    
    public static InventoryResponse getInstance(Response response) {
        return new InventoryResponse(response);
    }
    
    @Override
    public Boolean isSuccessful() {
        return successMessage.equals(statusInfo.getReasonPhrase());
    }

    @Override
    public List<ProductCalibratable> getProducts() {
        return new ArrayList<>(this.products);
    }
}
